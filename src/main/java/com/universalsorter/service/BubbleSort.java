package com.universalsorter.service;

import java.util.Comparator;

public class BubbleSort<T> {

    private final Comparator<T> comparator;

    public BubbleSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void bubbleSort(T[] array) {
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