package com.practice;

import java.util.Arrays;

public class SortedArrayMerge {

    public static void main(String[] args) {
        int[] array = merge(new int[]{1, 3, 4, 6, 8, 9}, new int[]{5, 7, 10, 12, 16});
        Arrays.stream(array)
                .forEach(value -> System.out.println(value));
    }

    static int[] merge(int[] left, int[] right) {
        int i = 0;
        int j = 0;

        int k = 0;
        int[] array = new int[left.length + right.length];
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

        return array;
    }
}
