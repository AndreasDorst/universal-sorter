package com.universalsorter.model;
import java.util.Locale;

public class Book implements Storable,Comparable {


    private final String author;
    private final String title;
    private final Integer numberOfPages;


    private Book(String author, String title, Integer numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }



    public static Book.Builder builder() {
        return new Book.Builder();
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException("Сравниваемый объект не может быть null");
        }
        if (!(o instanceof Book)) {
            throw new ClassCastException("Объект должен быть типа Book");
        }

        Book book = (Book) o;

        // Сравнение по автору
        int authorComparison = this.author.compareTo(book.author);
        if (authorComparison != 0) {
            return authorComparison;
        }

        // Если авторы одинаковые, сравниваем по названию книги
        int titleComparison = this.title.compareTo(book.title);
        if (titleComparison != 0) {
            return titleComparison;
        }

        // Если названия одинаковые, сравниваем по количеству страниц
        return this.numberOfPages.compareTo(book.numberOfPages);
    }



    public static class Builder {
        private String author;
        private String title;
        private Integer numberOfPages;

        private void validate() {
            final int MINIMUM_VALUE = 1; // минимальное количество страниц
            final int MAXIMUM_VALUE = 21450; // максимальное количество страниц
            final String ERROR_MESSAGE = "Введены некорректные данные"; // сообщение об ошибке

            if (author == null || title == null || author.isEmpty() || title.isEmpty()
                    || numberOfPages < MINIMUM_VALUE || numberOfPages > MAXIMUM_VALUE) {
                throw new IllegalStateException(ERROR_MESSAGE);
            }

        }

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
            validate();
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


    @Override
    public String serialize() {
        return String.format(Locale.US,"Book,%s,%s,%d", author, title, numberOfPages);
    }

    @Override
    public Book deserialize(String data) {
        String[] parts = data.split(",");
        if (parts.length != 4 || !"Book".equals(parts[0])) {
            throw new IllegalArgumentException("Некорректные данные для десериализации Book: " + data);
        }
        return Book.builder()
                .author(parts[1])
                .title(parts[2])
                .page(Integer.parseInt(parts[3]))
                .build();
    }

    @Override
    public String toString() {
        return "Автор: " + author + "\n" +
                "Название книги: " + title + "\n" +
                "Количество страниц: " + numberOfPages +"\n";

    }
}
