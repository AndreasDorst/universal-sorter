package com.universalsorter.service;

public class MergeSort {
    public static void mergeSort(int[] inputArray) {
        if (inputArray.length < 2) {
            return;
        }

        int middle = inputArray.length / 2;
        int[] leftPart = new int[middle];
        int[] rightPart = new int[inputArray.length - middle];

        System.arraycopy(inputArray, 0, leftPart, 0, middle);
        System.arraycopy(inputArray, middle, rightPart, 0, inputArray.length - middle);

        mergeSort(leftPart);
        mergeSort(rightPart);

        merge(inputArray, leftPart, rightPart);
    }

    public static void merge(int[] inputArray, int[] leftPart, int[] rightPart) {
        int i = 0, j = 0, k = 0;

        while (i < leftPart.length && j < rightPart.length) {
            if (leftPart[i] <= rightPart[j]) {
                inputArray[k++] = leftPart[i++];
            }
            else {
                inputArray[k++] = rightPart[j++];
            }
        }

        while (i < leftPart.length) {
            inputArray[k++] = leftPart[i++];
        }

        while (j < rightPart.length) {
            inputArray[k++] = rightPart[j++];
        }
    }

    public static void main(String[] args) {
        int[] inputArray = {12, 5, 999, 350, 95};

        mergeSort(inputArray);

        for (int numbers : inputArray) {
            System.out.print(numbers + " ");
        }
    }
}
