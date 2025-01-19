package com.universalsorter.service;

public class SortedArraySearch {
    public static <T extends Comparable<T>> String getElement(T[] sortedArray,  T target) {
    	String result;
    	int index = getElementIndex(sortedArray, target);
    	
    	if (index == -1) {
    		result = "Данный элемент в массиве отсутствует.";
		} else {
    		result = "Данный элемент находится в массиве по индексу: " + index;
		}
    	
    	return result;
    }	
	
    public static <T extends Comparable<T>> int getElementIndex(T[] sortedArray, T target) {
        int left = 0;
        int right = sortedArray.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            T obj = sortedArray[mid];
            int compareResult = obj.compareTo(target);
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
