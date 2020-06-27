package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Find longest sequence of zeros in binary representation of an integer.
* */
public class BinaryGap {

    public static void main(String[] args) {

        for (int index= 0; index <= 10; index++) {
            findBinaryGap(index);
            System.out.println("");
        }
    }

    public static int findBinaryGap(int number) {
        int[] binaryCode = findBinaryCode(number);
        boolean countingStarted = false;
        int maxCount = 0;
        int currCount = 0;
        for (int index = 0; index < binaryCode.length; index++) {
            if (binaryCode[index] == 1) {
                if (countingStarted) {
                    if (currCount > maxCount) {
                        maxCount = currCount;
                    }

                    currCount = 0;
                } else {
                    countingStarted = true;
                }
            } else {
                if (countingStarted) {
                    currCount++;
                }
            }
        }

        System.out.print(" Binary gap: " + maxCount);
        return maxCount;
    }

    public static int[] findBinaryCode(int number) {
        List<Integer> binarySequence = new ArrayList();
        System.out.print("Binary of " + number + " is: ");

        if (number == 0) {
            binarySequence.add(0);
        }

        while (number != 0) {
            binarySequence.add(number % 2);
            number = number/2;
        }

        int[] arr = intListToArray(binarySequence);
        Reverse.reverseArray(arr);

        Arrays.stream(arr)
                .filter(Integer.class :: isInstance)
                .map(Integer.class :: cast)
                .forEach(bit -> System.out.print(bit));
        System.out.print(".");
        return arr;
    }

    private static int[] intListToArray(List<Integer> intList) {
        int[] arr = new int[intList.size()];
        for (int index = 0 ; index < intList.size(); index++) {
            arr[index] = intList.get(index);
        }

        return arr;
    }
}
