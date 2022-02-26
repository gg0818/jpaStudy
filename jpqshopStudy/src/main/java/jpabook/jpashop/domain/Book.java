package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item{
    private String author;
    private String isbns;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbns() {
        return isbns;
    }

    public void setIsbns(String isbns) {
        this.isbns = isbns;
    }
}
