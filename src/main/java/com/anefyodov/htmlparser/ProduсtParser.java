package com.anefyodov.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ProduсtParser {
    private final String content;
    private final Document document;

    public ProduсtParser(String content) {
        this.content = content;
        document = Jsoup.parse(this.content);

    }

//    public Optional<String> getPreviousLink() {
//        final String link = document.body().getElementsByClass("paginateButtons").get(0).getElementsByClass("prevLink")
//                .attr("href");
////        final Element elementById = document.body().getElementById("div.paginageButtons");
////        System.out.println(elementById);
//
//        return Optional.ofNullable(link);
//    }
//
//    public Optional<String> getNextLink() {
//        final String link = document.body().getElementsByClass("paginateButtons").get(0).getElementsByClass("nextLink")
//                .attr("href");
////        final Element elementById = document.body().getElementById("div.paginageButtons");
////        System.out.println(elementById);
//
//        return Optional.ofNullable(link);
//    }


    public List<Ingr> getIngrList() {
        List<Ingr> list = new ArrayList<>();
        final Element tbody = document.body().getElementsByTag("table").get(0).getElementsByTag("table").get(0)
                .getElementsByTag("tbody").get(0);
        Elements trs = tbody.getElementsByTag("tr");
        for (Element tr : trs) {
            String section = tr.getElementsByTag("td").get(0).textNodes().get(0).text().trim();
            String nutrient = tr.getElementsByTag("td").get(1).textNodes().get(0).text().trim();
            String unit = tr.getElementsByTag("td").get(2).textNodes().get(0).text().trim();
            String oz = tr.getElementsByTag("td").get(3).textNodes().get(0).text().trim();
            String value = tr.getElementsByTag("td").get(4).textNodes().get(0).text().trim();
            list.add(new Ingr(section, nutrient, unit, oz, value));
        }
        return list;
    }
}

