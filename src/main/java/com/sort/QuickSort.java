package com.sort;

import java.util.Arrays;

/*
* Quick sort is also a divide and conquer algorithm.
* Here we first choose a pivot point, in this case the right most element.
* Then we iterate over rest of the elements and check if its lesser or higher than pivot value.
* If its higher, do nothing and increment the counter.
* If its lower than pivot value, then swap it with the earliest highest value already iterated upon.
* Once done, finally replace the available replacing index value to pivot value as all lesser elements
* are already on the left side and after this all right side elements will be bigger then pivot value.
* Send this pivot index to recursively call the sort for shorter arrays.
*
* Note: Do not include pivot index for partitioning as its already used for higher index.
*       So the next partition would be (lower, pivot-1), (pivot+1, higher)
* */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[] {9, 10, 4, 34, 3, 6, 2, 8, 5, 43, 35};
        sort(arr, 0, arr.length - 1);
        Arrays.stream(arr)
                .forEach(value -> System.out.println(value));
    }

    static void sort(int[] array, int lowerIndex, int higherIndex) {
        if (higherIndex > lowerIndex) {
            int pivot = partition(array, lowerIndex, higherIndex);
            sort(array, lowerIndex, pivot-1);
            sort(array, pivot+1, higherIndex);
        }
    }

    static int partition(int[] array, int lowerIndex, int higherIndex) {
        int pivot = array[higherIndex];

        int i = lowerIndex;
        int j = lowerIndex;
        while(i < higherIndex) {
            if (array[i] < pivot) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                j++;
            }

            i++;
        }

        int temp = array[j];
        array[j] = array[higherIndex];
        array[higherIndex] = temp;

        return j;
    }
}
