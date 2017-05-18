package com.anefyodov.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListParser {
    private final String content;
    private final Document document;

    public ListParser(String content) {
        this.content = content;
        document = Jsoup.parse(this.content);

        //Создаём объект HtmlCleaner
//        HtmlCleaner cleaner = new HtmlCleaner();
//        //Загружаем html код сайта
//        rootNode = cleaner.clean(htmlPage);

    }

    public Optional<String> getPreviousLink() {
        final String link = document.body().getElementsByClass("paginateButtons").get(0).getElementsByClass("prevLink")
                .attr("href");
//        final Element elementById = document.body().getElementById("div.paginageButtons");
//        System.out.println(elementById);

        return Optional.ofNullable(link);
    }

    public Optional<String> getNextLink() {
        final String link = document.body().getElementsByClass("paginateButtons").get(0).getElementsByClass("nextLink")
                .attr("href");
//        final Element elementById = document.body().getElementById("div.paginageButtons");
//        System.out.println(elementById);

        return Optional.ofNullable(link);
    }


    public List<Product> getProductList() {
        List<Product> list = new ArrayList<>();
        final Element tbody = document.body().getElementsByClass("list-left").get(0).getElementsByTag("table").get(0)
                .getElementsByTag("tbody").get(0);
        Elements trs = tbody.getElementsByTag("tr");
        for (Element tr : trs) {
            String ndb = tr.getElementsByTag("td").get(1).child(0).textNodes().get(0).text().trim();
            String desc = tr.getElementsByTag("td").get(2).child(0).textNodes().get(0).text().trim();
            String man = tr.getElementsByTag("td").get(3).child(0).textNodes().get(0).text().trim();
            String link = tr.getElementsByTag("td").get(1).getElementsByTag("a").get(0).attr("href");
            list.add(new Product(ndb, desc, man, link));
        }
        return list;
    }
}
