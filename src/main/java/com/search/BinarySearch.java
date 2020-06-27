package com.search;

/*
* Only applicable on sorted data.
* */
public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(search(new int[] {4}, 4));
        System.out.println(search(new int[] {4, 2}, 4));
        System.out.println(search(new int[] {2, 4}, 4));
        System.out.println(search(new int[] {1, 4, 6, 7, 9}, 4));
        System.out.println(search(new int[] {1, 4, 6, 7, 9, 12}, 4));
        System.out.println(search(new int[] {1, 4, 6, 7, 9, 13, 15}, 14));
        System.out.println(search(new int[] {1, 4, 6, 7, 9, 13, 15, 18}, 14));
        System.out.println(search(new int[] {1, 4, 6, 7, 9, 13, 15, 18, 19}, 19));
    }

    static int search(int[] array,  int number ) {
        int i = 0;
        int j = array.length -1;
        int index = (i + j)/2;

        while(i <= j) {
            if (number < array[index]) {
                j = index - 1;
            } else if (number > array[index]) {
                i = index + 1;
            } else {
                return index;
            }

            index = (i + j)/2;
        }

        return -1;
    }
}
