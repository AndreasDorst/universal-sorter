package com.universalsorter.repository;
import com.universalsorter.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final List<Book> BOOKS = new ArrayList<>();
    static {
        BOOKS.add(new Book.Builder().title("Война и мир").author("Лев Толстой").page(550).build());
        BOOKS.add(new Book.Builder().title("Таинственный остров").author("Жюль Верн").page(340).build());
        BOOKS.add(new Book.Builder().title("Преступление и наказание").author("Фёдор Достоевский").page(287).build());
        BOOKS.add(new Book.Builder().title("Всадник без головы").author("Майн Рид").page(258).build());
        BOOKS.add(new Book.Builder().title("Мёртвые Души").author("Николай Гоголь").page(411).build());
        BOOKS.add(new Book.Builder().title("Дети капитана Гранта").author("Жюль Верн").page(381).build());
    }

    public Book getBook(int i){
        if(i>=BOOKS.size()){return null;}
        return  BOOKS.get(i);
    }
    public int getSizeBookList(){
        return BOOKS.size();
    }
}
