package com.sort;

import java.util.Arrays;

/*
* In insertion sort, we are comparing (i)th element of an array with all its previous elements (meanwhile moving each
* element to the right) un till we find the one say (j)th element which is lesser than (i)th and then will the replace
* (i)th value with (j+1)th (which must be now empty during last iteration).
*
* This way we are inserting the (i)th element in the middle of the array depending on its value in the sorted order.
*
* */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[] {3, 6, 2, 8, 5, 9, 10, 4, 34, 43, 35};
        sort(arr);
        Arrays.stream(arr)
                .forEach(value -> System.out.println(value));

    }

    static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > value) {
                array[j + 1] = array[j];
                j--;
            }

            array[j+1] = value;
        }
    }
}
