package com.anefyodov.htmlparser;

public class Product {
    private final String ndb;
    private final String desc;
    private final String man;
    private final String link;

    public Product(String ndb, String desc, String man, String link) {
        this.ndb = ndb;
        this.desc = desc;
        this.man = man;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ndb='" + ndb + '\'' +
                ", desc='" + desc + '\'' +
                ", man='" + man + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
