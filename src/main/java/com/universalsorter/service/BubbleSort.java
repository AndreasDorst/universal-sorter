package com.universalsorter.service;

import java.util.Comparator;

public class BubbleSort<T> implements SortingStrategy<T>{

    @Override
    public void sort(T[] array, Comparator<T> comparator) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
}