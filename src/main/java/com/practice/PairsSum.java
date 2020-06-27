package com.practice;

/*
* Find the pair of numbers in a sorted integer array whose sum is equal to the given number.
* */
public class PairsSum {

    public static void main(String[] args) {
        pairAvailable(new int[]{2, 3, 6, 7, 8, 9, 9, 10}, 18);
    }

    static void pairAvailable(int[] array, int number) {
        int i = 0;
        int j = array.length -1;
        while (i != j) {
            int sum = array[i] + array[j];
            if (sum == number) {
                System.out.println("array[" + i + "] = " + array[i] +", array[" + j + "] = " + array[j]);
                j--;
            } else if (sum < number) {
                i++;
            } else {
                j--;
            }
        }
    }
}
