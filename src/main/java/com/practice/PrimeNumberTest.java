package com.practice;

public class PrimeNumberTest {

    public static void main(String[] args) {
        getPrimeNumber(100);
    }

    static int[] getPrimeNumber(int n) {
        int [] primeNumbers = new int [n];
        int primeNumberIndex = 0;
        int index = n;
        while (index >= 1) {
            int innerIndex = index -1;
            boolean isPrime = true;
            while (innerIndex > 1) {
                if (index % innerIndex == 0) {
                    isPrime = false;
                    break;
                }

                innerIndex--;
            }

            if (isPrime) {
                System.out.print(" " + index);
                primeNumbers[primeNumberIndex] = index;
                primeNumberIndex++;
            }

            index--;
        }

        return primeNumbers;
    }
}
