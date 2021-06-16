package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.html.model.Post;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;

public class SqlRuParse {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(
                "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-"
                        + "devops-moskva-do-200t").get();
        Elements msgBody = document.select(".msgBody");
        Element msgFooter = document.selectFirst(".msgFooter");
        String name = document.select(".messageHeader").get(0).text();
        String href = msgFooter.baseUri();
        Element data = msgBody.get(1);
        LocalDateTime time = new SqlRuDateTimeParser().parse(msgFooter.text().split("\\[")[0]);
        Post post = new Post(
                name,
                data.text(),
                href,
                time
        );
        System.out.println(post);
    }
}
