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
        BOOKS.add(new Book.Builder().title("Анна Каренина").author("Лев Толстой").page(864).build());
        BOOKS.add(new Book.Builder().title("1984").author("Джордж Оруэлл").page(328).build());
        BOOKS.add(new Book.Builder().title("Мастер и Маргарита").author("Михаил Булгаков").page(384).build());
        BOOKS.add(new Book.Builder().title("Гарри Поттер и философский камень").author("Джоан Роулинг").page(320).build());
        BOOKS.add(new Book.Builder().title("Улисс").author("Джеймс Джойс").page(732).build());
        BOOKS.add(new Book.Builder().title("Три товарища").author("Эрих Мария Ремарк").page(448).build());
        BOOKS.add(new Book.Builder().title("Маленький принц").author("Антуан де Сент-Экзюпери").page(96).build());
        BOOKS.add(new Book.Builder().title("Тихий Дон").author("Михаил Шолохов").page(1600).build());
        BOOKS.add(new Book.Builder().title("О дивный новый мир").author("Олдос Хаксли").page(288).build());
        BOOKS.add(new Book.Builder().title("Сто лет одиночества").author("Габриэль Гарсиа Маркес").page(416).build());
        BOOKS.add(new Book.Builder().title("Лолита").author("Владимир Набоков").page(336).build());
        BOOKS.add(new Book.Builder().title("Братья Карамазовы").author("Фёдор Достоевский").page(824).build());
        BOOKS.add(new Book.Builder().title("Граф Монте-Кристо").author("Александр Дюма").page(1312).build());
        BOOKS.add(new Book.Builder().title("Дон Кихот").author("Мигель де Сервантес").page(1072).build());
        BOOKS.add(new Book.Builder().title("Фауст").author("Иоганн Вольфганг фон Гёте").page(480).build());
        BOOKS.add(new Book.Builder().title("Портрет Дориана Грея").author("Оскар Уайльд").page(254).build());
        BOOKS.add(new Book.Builder().title("Над пропастью во ржи").author("Джером Сэлинджер").page(234).build());
        BOOKS.add(new Book.Builder().title("Остров сокровищ").author("Роберт Льюис Стивенсон").page(304).build());
        BOOKS.add(new Book.Builder().title("Алиса в Стране чудес").author("Льюис Кэрролл").page(200).build());
        BOOKS.add(new Book.Builder().title("Грозовой перевал").author("Эмили Бронте").page(416).build());
    }

    public Book getBook(int i){
        if(i>=BOOKS.size()){return null;}
        return  BOOKS.get(i);
    }
    public int getSizeBookList(){
        return BOOKS.size();
    }
}
