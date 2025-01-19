package com.universalsorter.model;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;


/**
 * Class book with properties <b>author</b> and <b>title</b> and <b>numberOfPages</b>  .
 * {@code @autor} Аднрей, Евгений, Александр
 * @version 1.1
 */
public class Book implements Storable,Comparable<Book>, Comparator<Book> {

    /**
     * Author of the book
     * --GETTER--
     *  Return the author of the book.
     * */
    private final String author;
    /**
     * Title of the book
     * --GETTER--
     *  Return the title of the book.
     * */
    private final String title;
    /**
     * Number of pages of the book
     * --GETTER--
     *  Return the number of pages of the book.
     * */
    private final Integer numberOfPages;

    /**
     * Конструктор - создание нового объекта
     * @param author - автор
     * @param title - название
     * @param numberOfPages - кол-во страниц
     * @see Book#Book(String author, String title, Integer numberOfPages)
     */
    private Book(String author, String title, Integer numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }


    /**
     * Метод для создания объекта Book {@link Book}
     * @return возвращает объект класса {@link Book}
     */
    public static Book.Builder builder() {
        return new Book.Builder();
    }

    /**
     *  Переопределенный метод интерфейса Comparable {@link Comparable}
     * @param book объект для сравнения
     * @return отрицательное целое число, ноль или положительное целое число, поскольку этот объект меньше, равен или больше указанного объекта
     * @throws NullPointerException  если в параметры передается null
     */
    @Override
    public int compareTo(Book book) {
        if (book == null) {
            throw new NullPointerException("Сравниваемый объект не может быть null");
        }
//        if (!(o instanceof Book)) {
//            throw new ClassCastException("Объект должен быть типа Book");
//        }
//
//        Book book = (Book) o;

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


    /**
     *  Переопределенный метод интерфейса Comparator {@link Comparator}
     * @param book1 первая книга для сравнения.
     * @param book2 вторая книга для сравнения.
     * @return отрицательное целое число, ноль или положительное целое число, поскольку первый аргумент меньше, равен или больше второго.
     * @throws NullPointerException  если в параметры передается null
     */
    @Override
    public int compare(Book book1, Book book2) {
        if (book1 == null || book2 == null) {
            throw new NullPointerException("Сравниваемые объекты не могут быть null");
        }
        int authorComparison = book1.getAuthor().compareTo(book2.getAuthor());
        if (authorComparison != 0) {
            return authorComparison;
        }

        int titleComparison = book1.getTitle().compareTo(book2.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        }

        return book1.getNumberOfPages().compareTo(book2.getNumberOfPages());
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


    /**
     * Метод для получения значения поля {@link Book#author}
     * @return возвращает имя автора книги {@link String}.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Метод для получения значения поля {@link Book#title}
     * @return возвращает название книги {@link String}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод для получения значения поля {@link Book#numberOfPages}
     * @return возвращает кол-во страниц в книге {@link Integer}
     */
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    /**
     *  Переопределенный метод интерфейса {@link Storable} для сохранения объектов класса {@link Book}.
     * @return строковое представление {@link String} сохраняемого объекта {@link Book}
     */
    @Override
    public String serialize() {
        return String.format(Locale.US,"Book,%s,%s,%d", author, title, numberOfPages);
    }

    /**
     *  Переопределенный метод интерфейса {@link Storable} восстановления сохраненного объекта класса {@link Book}.
     * @return объект класса {@link Book}
     */
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



    /**
     * Указывает, равен ли какой-либо другой объект этому объекту, класс объектов {@link Book}. Этот метод должен подчиняться общему договору {@link Object#equals(Object)}.
     * @param o объект для сравнения.
     * @return {@code true} только если указанный объект также {@link Book} и имеет одинаковые поля.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(numberOfPages, book.numberOfPages);
    }

    /**
     * Переопределенный метод класса {@link Object}, который генерирует и возвращает хэш-кода для объекта {@link Book}. Этот метод должен подчиняться общему договору {@link Object#hashCode()}.
     * @return возвращает значение хэш-кода для этого объекта класса {@link Book}
     */
    @Override
    public int hashCode() {
        return Objects.hash(author, title, numberOfPages);
    }

    /**
     * Переопределенный метод класса {@link Object}, возвращает строковое представление объекта. {@link Book}. Этот метод должен подчиняться общему договору {@link Object#hashCode()}.
     * @return возвращает строковое представление объекта класса {@link Book}
     */
    @Override
    public String toString() {
        return "Автор: " + author + "\n" +
                "Название книги: " + title + "\n" +
                "Количество страниц: " + numberOfPages +"\n";

    }
}