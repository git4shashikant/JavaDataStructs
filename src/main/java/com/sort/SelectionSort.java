package com.sort;

import java.util.Arrays;

/*
* Its called selection index because you are selecting an index on each iteration to compare with
* other elements and swap (if required) to set the min value at that index. When you repeat this n
* number of times, all minValues will be set from left to right and the array will be sorted.
* */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[] {3, 6, 2, 8, 5, 9, 10, 4, 34, 43, 35};
        sort(arr);
        Arrays.stream(arr)
                .forEach(value -> System.out.println(value));

    }

    static void sort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] =  temp;
                }
            }
        }
    }
}
