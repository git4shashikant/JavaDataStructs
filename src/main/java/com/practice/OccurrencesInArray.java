package com.practice;

/*
* Find value that occurs in odd number of elements.
* Using map its quite simple to iterate over the array and keep counts for each number.
* Finally look for the ones with odd values.
* This example is without map.
* */
public class OccurrencesInArray {

    public static void main(String[] args) {
        int[] array = new int[] {1, 3, 2, 1, 1, 4, 5, 3, 2, 4};
        int[] counts = numberCountUsingMap(array);

        int index = 0;
        while (index < counts.length) {
            System.out.println("Number: " + index + ", Count: " + counts[index]);
            index++;
        }
    }

    public static int[] numberCountUsingMap(int[] array) {
        //array to keep counts of numbers in their respective index
        int[] counts = new int[10];

        int index = 0;
        while (index < array.length) {
            int number = array[index];
            counts[number] = counts[number] + 1;
            index++;
        }

        return counts;
    }

    public static int[] numberCountUsingPointers(int[] array) {
        return new int[]{};
    }
}
