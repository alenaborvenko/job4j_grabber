package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SqlRuParse {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = document.select(".postslisttopic");
        Elements rowDate = document.select(".altCol");
        int i = 1;
        for (Element el : row) {
            System.out.println(rowDate.get(i).text());
            Element href = el.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            i += 2;
        }
    }
}
