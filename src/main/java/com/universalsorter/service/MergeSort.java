package com.universalsorter.service;

import com.universalsorter.model.Book;

import java.util.Arrays;

public class MergeSort {

    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array == null || array.length < 2) {
            return; // Нет смысла сортировать массивы, содержащих меньше 2-ух элементов
        }

        T[] tempArray = Arrays.copyOf(array, array.length); // Временный массив для объединения
        mergeSortHelper(array, tempArray, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSortHelper(T[] array, T[] tempArray, int left, int right) {
        if (left >= right) {
            return; // Базовы вариант: один элемент
        }

        int mid = left + (right - left) / 2;

        // Рекурсивная сортировка левой и правой части
        mergeSortHelper(array, tempArray, left, mid);
        mergeSortHelper(array, tempArray, mid + 1, right);

        // Соединение отсортированных частей
        merge(array, tempArray, left, mid, right);
    }

    private static <T extends Comparable<T>> void merge(T[] array, T[] tempArray, int left, int mid, int right) {
        // Копирование соответствующего подмассива во временный массив
        for (int i = left; i <= right; i++) {
            tempArray[i] = array[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        // Объединение двух частей обратно в исходный массив
        while (i <= mid && j <= right) {
            if (tempArray[i].compareTo(tempArray[j]) <= 0) {
                array[k++] = tempArray[i++];
            } else {
                array[k++] = tempArray[j++];
            }
        }

        // Копирование оставшихся элементов из левой части (если есть)
        while (i <= mid) {
            array[k++] = tempArray[i++];
        }

        // Копировать правую часть нет смысла т.к. она уже на месте
    }

    public static void main(String[] args) {
        // Пример использования
        Integer[] numbers = {5, 2, 9, 1, 5, 6};
        mergeSort(numbers);
        System.out.println("Sorted numbers: " + Arrays.toString(numbers));

        String[] words = {"banana", "apple", "cherry", "date"};
        mergeSort(words);
        System.out.println("Sorted words: " + Arrays.toString(words));
    }
}
