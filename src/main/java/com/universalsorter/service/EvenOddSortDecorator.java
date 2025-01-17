package com.universalsorter.service;

import com.universalsorter.model.Book;
import com.universalsorter.model.Car;
import com.universalsorter.model.RootVegetable;

import java.util.*;

/**
 * Класс используется для быстрой сортировки  .
 * {@code @autor} Богинь Александр
 * @version 1.0
 */
public class EvenOddSortDecorator {

    /**
     * Метод получает <code>List<Car> cars</code>
     * и возвращает отсортированную коллекцию машин
     * (сортируются машины с четным годом выпуска, машины с нечетным остаются в той же позиции).
     *
     * @param listCars Коллекция машин.
     *
     */


    public static void sortCarsByYearOfProduction(List<Car> listCars) {
        List<Car> evenCars = new ArrayList<>();
        // Собираем коллекцию Car с четным значением weight
        for (Car car : listCars) {
            if (car.getYearOfProduction() % 2 == 0) {
                evenCars.add(car);
            }
        }
        // Сортируем коллекцию (Применяя Comparator.comparing(Car::getYearOfProduction))
        evenCars.sort(Comparator.comparing(Car::getYearOfProduction));


        // Помещаем отсортированные овощи обратно в исходный список.
        Iterator<Car> evenIterator = evenCars.iterator();
        for (int i = 0; i < listCars.size(); i++) {
            if (listCars.get(i).getYearOfProduction() % 2 == 0 && evenIterator.hasNext()) {
                listCars.set(i, evenIterator.next());
            }
        }
    }

    /**
     * Метод получает <code>List<RootVegetable> rootVegetableList</code>
     * и возвращает отсортированную коллекцию rootVegetable
     * (сортируются rootVegetable с четным значением веса, rootVegetable с нечетным остаются в той же позиции).
     *
     * @param rootVegetableList Коллекция машин.
     *
     */

    public static void sortRootVegetables(List<RootVegetable> rootVegetableList) {
        List<RootVegetable> evenWeightVegetables = new ArrayList<>();

// Собираем коллекцию RootVegetable с четным значением weight
        for (RootVegetable vegetable : rootVegetableList) {
            if (vegetable.getWeight() % 2 == 0) {
                evenWeightVegetables.add(vegetable);
            }
        }

// Сортируем коллекцию (в классе реализован Сomparable interface)
        Collections.sort(evenWeightVegetables);

// Помещаем отсортированные овощи обратно в исходный список.
        int evenIndex = 0;
        for (int i = 0; i < rootVegetableList.size(); i++) {
            if (rootVegetableList.get(i).getWeight() % 2 == 0) {
                rootVegetableList.set(i, evenWeightVegetables.get(evenIndex++));
            }
        }
    }

    /**
     * Метод получает <code>List<Book> bookList</code>
     * и возвращает отсортированную коллекцию книг
     * (сортируются книги с четным колличеством страниц, книги с нечетным остаются в той же позиции).
     *
     * @param bookList Коллекция машин.
     *
     */

    public static void sortBooks(List<Book> bookList) {
        List<Book> evenPageBooks = new ArrayList<>();

// // Собираем коллекцию Book с четным значением numberOfPages
        for (Book book : bookList) {
            if (book.getNumberOfPages() % 2 == 0) {
                evenPageBooks.add(book);
            }
        }

// // Сортируем коллекцию применяя сортировку пузырьком
        for (int i = 0; i < evenPageBooks.size() - 1; i++) {
            for (int j = 0; j < evenPageBooks.size() - i - 1; j++) {
                if (evenPageBooks.get(j).compareTo(evenPageBooks.get(j + 1)) > 0) {
                    Book temp = evenPageBooks.get(j);
                    evenPageBooks.set(j, evenPageBooks.get(j + 1));
                    evenPageBooks.set(j + 1, temp);
                }
            }
        }

//  // Помещаем отсортированные овощи обратно в исходный список.
        int evenIndex = 0;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getNumberOfPages() % 2 == 0) {
                bookList.set(i, evenPageBooks.get(evenIndex++));
            }
        }
    }

}
