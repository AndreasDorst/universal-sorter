package com.universalsorter.service;
import java.util.Comparator;

public class MergeSortTemp<T> implements SortingStrategy<T>{

    @Override
    public void sort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length <= 1) {
            return; // Массив уже отсортирован или пуст
        }
        mergeSort(array, 0, array.length - 1, comparator);
    }

    private void mergeSort(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            // Находим середину массива
            int mid = left + (right - left) / 2;

            // Рекурсивно сортируем левую и правую половины
            mergeSort(array, left, mid, comparator);
            mergeSort(array, mid + 1, right, comparator);

            // Объединяем отсортированные половины
            merge(array, left, mid, right, comparator);
        }
    }

    private void merge(T[] array, int left, int mid, int right, Comparator<T> comparator) {
        // Размеры временных массивов
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Создаем временные массивы
        @SuppressWarnings("unchecked")
        T[] leftArray = (T[]) new Object[n1];
        T[] rightArray = (T[]) new Object[n2];

        // Копируем данные во временные массивы
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        // Индексы для слияния
        int i = 0, j = 0, k = left;

        // Слияние временных массивов обратно в основной массив
        while (i < n1 && j < n2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы из leftArray (если они есть)
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Копируем оставшиеся элементы из rightArray (если они есть)
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}