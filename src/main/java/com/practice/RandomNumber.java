package com.practice;

import java.util.Arrays;

public class RandomNumber {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4 , 5};
        Arrays.stream(random(arr))
                .forEach(ele -> System.out.println(ele));
    }

    private static int[] random(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int randomIndex = (int) (Math.random() * (arr.length-1 - i)) + i;
            int temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }

        return arr;
    }

}
