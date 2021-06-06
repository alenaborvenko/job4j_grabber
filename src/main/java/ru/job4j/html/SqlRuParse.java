package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;

public class SqlRuParse {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = document.select(".postslisttopic");
        Elements rowDate = document.select(".altCol");
        int i = 1;
        for (Element el : row) {
            String timeHTML = rowDate.get(i).text();
            LocalDateTime time = new SqlRuDateTimeParser().parse(timeHTML);
            System.out.println(time);
            Element href = el.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            i += 2;
        }
    }
}
