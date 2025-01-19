package com.universalsorter.service;

import java.util.Comparator;

public class SortingContext <T>{
    private SortingStrategy<T> strategy;

    public SortingContext(SortingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void sortArray(T[] array, Comparator<T> comparator) {

        strategy.sort(array, comparator);
    }

}