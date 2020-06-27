package com.sort;

import java.util.Arrays;

/*
* Here, we keep on comparing and swapping (if required), (kth & k+1th) then (k+1th & k+2th) elements onwards.
* This we iterate n times to get the sorted array.
* */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[] {3, 6, 2, 8, 5, 9, 10, 4, 34, 43, 35};
        sort(arr);
        Arrays.stream(arr)
                .forEach(value -> System.out.println(value));

    }

    static void sort(int[] array) {
        int outerIndex = 0;
        while(outerIndex < array.length) {
            int innerIndex = 0;
            while(innerIndex < array.length -1) {
                if (array[innerIndex+1] < array[innerIndex]) {
                    int temp = array[innerIndex];
                    array[innerIndex] = array[innerIndex+1];
                    array[innerIndex+1] =  temp;
                }

                innerIndex++;
            }

            outerIndex++;
        }
    }
}
