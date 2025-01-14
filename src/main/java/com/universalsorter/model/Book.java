package com.universalsorter.model;
import lombok.Getter;


public class Book {


    private final String author;
    private final String title;
    private final Integer numberOfPages;

    public Book(String author, String title, Integer numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }



    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String author;
        private String title;
        private Integer numberOfPages;


        public Book.Builder author(String author) {
            this.author = author;
            return this;
        }

        public Book.Builder title(String title) {
            this.title = title;
            return this;
        }

        public Book.Builder page(Integer numberOfPages) {
            this.numberOfPages = numberOfPages;
            return this;
        }

        public Book build() {
            if (author == null || title == null || numberOfPages <1) {
                throw new IllegalStateException("Введены некорректные данные");
            }
            return new Book(author, title, numberOfPages);
        }
    }
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }
}
