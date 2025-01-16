package com.universalsorter.service;

import com.universalsorter.model.Car;
import com.universalsorter.model.Book;

import java.util.Arrays;
import java.util.List;


/**
 * Класс используется для быстрой сортировки .
 * {@code @autor} Богинь Александр
 * @version 1.0
 */

public class QuickSort {


    /**
     * Метод получает <code>List<Car> cars</code>
     * и возвращает отсортированную коллекцию машин по году производства.
     *
     * @param listCar Коллекция машин.
     *
     */

    public static List<Car> quickSortCarsByYearOfProduction (List<Car> listCar){
        Car[] array = new Car[listCar.size()];
        for (int i = 0; i < listCar.size(); i++){
            array[i] = listCar.get(i);
        }
        quickSort(array, 0, array.length - 1);
        return Arrays.asList(array);
    }


    /**
     * Метод получает <code>List<Book> Books</code>
     * и возвращает отсортированную коллекцию книг по колличеству страниц.
     *
     * @param listBook Коллекция книг.
     *
     */

    public static List<Book> quickSortBooksByNumberOfPages(List<Book> listBook){
        Book[] array = new Book[listBook.size()];
        for (int i = 0; i < listBook.size(); i++){
            array[i] = listBook.get(i);
        }
        quickSort(array, 0, array.length - 1);
        return Arrays.asList(array);
    }

    /**
     * Метод получает <code>Car[] cars</code>, <code>int low</code>, <code>int high</code>
     * и запускает сортировкку массива.
     *
     * @param cars Наши машины.
     * @param low Начало массива.
     * @param high Конец массива.
     *
     *
     */

    private static void quickSort(Car[] cars, int low, int high) {
        if (low < high) {
            int pi = carsSeparation(cars, low, high);
            quickSort(cars, low, pi - 1);
            quickSort(cars, pi + 1, high);
        }
    }

    /**
     * Метод получает <code>Car[] cars</code>, <code>int low</code>, <code>int high</code>
     * и используется для перестановки элементов массива вокруг точки поворота.
     *
     * @param cars Наши машины.
     * @param low указатель номера элемента в массиве.
     * @param high указатель номера элемента в массиве.
     *
     */

    private static int carsSeparation(Car[] cars, int low, int high) {
        Car car = cars[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (cars[j].getYearOfProduction() <= car.getYearOfProduction()) {
                i++;
                swap(cars, i, j);
            }
        }
        swap(cars, i + 1, high);
        return i + 1;
    }


    /**
     * Метод получает <code>Book[] Books</code>, <code>int low</code>, <code>int high</code>
     * и запускает сортировкку массива.
     *
     * @param Books Наши книги.
     * @param low Начало массива.
     * @param high Конец массива.
     *
     *
     */

    private static void quickSort(Book[] Books, int low, int high) {
        if (low < high) {
            int pi = booksSeparation(Books, low, high);
            quickSort(Books, low, pi - 1);
            quickSort(Books, pi + 1, high);
        }
    }

    /**
     * Метод получает <code>Book[] Books</code>, <code>int low</code>, <code>int high</code>
     * и используется для перестановки элементов массива вокруг точки поворота.
     *
     * @param Books Наши машины.
     * @param low указатель номера элемента в массиве.
     * @param high указатель номера элемента в массиве.
     *
     */

    private static int booksSeparation(Book[] Books, int low, int high) {
        Book Book = Books[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (Books[j].getNumberOfPages() <= Book.getNumberOfPages()) {
                i++;
                swap(Books, i, j);
            }
        }
        swap(Books, i + 1, high);
        return i + 1;
    }


    /**
     * Метод получает <code>Object[] obj</code>, <code>int i</code>, <code>int j</code>
     * и меняет местами элементы массива.
     *
     * @param obj наш массив.
     * @param i указатель номера элемента в массиве.
     * @param j указатель номера элемента в массиве.
     *
     */
    private static void swap(Object[] obj, int i, int j) {
        Object temp = obj[i];
        obj[i] = obj[j];
        obj[j] = temp;
    }






}
