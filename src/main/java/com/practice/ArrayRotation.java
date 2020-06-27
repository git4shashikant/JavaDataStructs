package com.practice;

import java.util.Arrays;

/*
* Rotate an array to the right by a given number of steps.
* */
public class ArrayRotation {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4};

        rotateArrayStepByStep(arr, 3);
        System.out.print("using rotateArrayStepByStep: ");
        Arrays.stream(arr)
                .filter(Integer.class :: isInstance)
                .map(Integer.class :: cast)
                .forEach(bit -> System.out.print(bit));

        System.out.println("");

        arr = rotateArrayAllAtOnce(new int[] {1, 2, 3, 4}, 3);
        System.out.print("using rotateArrayAllAtOnce: ");
        Arrays.stream(arr)
                .filter(Integer.class :: isInstance)
                .map(Integer.class :: cast)
                .forEach(bit -> System.out.print(bit));
    }

    //good in less memory
    public static void rotateArrayStepByStep(int[] array, int rotations) {
        while (rotations != 0) {
            int firstElement = array[0];
            int index = 1;
            while (index < array.length) {
                array[index -1] = array[index];
                index++;
            }

            array[array.length -1] = firstElement;
            rotations--;
        }
    }

    //Better in performance, will take more memory than rotateArrayStepByStep
    public static int[] rotateArrayAllAtOnce(int[] array, int rotations) {
        int[] newArr = new int[array.length];

        int index = 0;
        while (index < array.length) {
            int locationIndex = 0;
            if (rotations <= index) {
                locationIndex = index - rotations;
            } else {
                locationIndex = array.length - (rotations - index);
            }

            newArr[locationIndex] = array[index];
            index++;
        }

        return newArr;
    }
}
