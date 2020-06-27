package com.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[] {9, 10, 4, 34, 3, 6, 2, 8, 5, 43, 35};
        sort(arr, 0, arr.length - 1);
        Arrays.stream(arr)
                .forEach(value -> System.out.println(value));

    }

    static void sort(int[] array, int lowerIndex, int higherIndex) {
        if (higherIndex > lowerIndex) {
            int median = (lowerIndex + higherIndex)/2;

            sort(array, lowerIndex, median);
            sort(array, median + 1, higherIndex);

            merge(array, lowerIndex, median, higherIndex);
        }
    }

    static void merge(int[] array, int lowerIndex, int median, int higherIndex) {

        int[] left = Arrays.copyOfRange(array, lowerIndex, median+1);
        int[] right = Arrays.copyOfRange(array, median+1, higherIndex+1);

        int i = 0;
        int j = 0;
        int k = lowerIndex;
        while (i < left.length && j < right.length) {
            if(left[i] < right[j]) {
                array[k] = left[i];
                i++;
            } else if(left[i] > right[j]) {
                array[k] = right[j];
                j++;
            } else {
                array[k] = left[i];
                k = k + 1;
                array[k] = right[j];
                i++;
                j++;
            }

            k++;
        }

        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
    }
}
