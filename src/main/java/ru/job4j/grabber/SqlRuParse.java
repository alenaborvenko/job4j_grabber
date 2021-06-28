package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.utils.DateTimeParser;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        try {
            Document document = Jsoup.connect(link).get();
            Elements rows = document.select(".postslisttopic");
            for (Element rowTopic : rows) {
                String href = rowTopic.child(0).absUrl("href");
                posts.add(detail(href));
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("Incorrect link");
        }
        return posts;
    }

    @Override
    public Post detail(String link) {
        Post post;
        try {
            Document document = Jsoup.connect(link).get();

            Elements msgBody = document.select(".msgBody");
            Element msgFooter = document.selectFirst(".msgFooter");
            String name = document.select(".messageHeader").get(0).text();
            String href = msgFooter.baseUri();
            Element data = msgBody.get(1);
            LocalDateTime time = dateTimeParser.parse(msgFooter.text().split(" \\[")[0]);
            post = new Post(name, data.text(), href, time);
        } catch (IOException e) {
            throw new IllegalArgumentException("Incorrect link");
        }
        return post;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(new SqlRuParse(new SqlRuDateTimeParser())
                    .list("https://www.sql.ru/forum/job-offers/" + i));
        }
    }
}
