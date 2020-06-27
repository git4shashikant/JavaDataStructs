package com.practice;

/*
* Given a sorted array containing only 0 and 1, you have to find the index where the transition from 0 to 1 happened.
* This should be the efficient way.
* */
public class ArrayTransitionPoint {

    public static void main(String[] args) {
        System.out.println(transitionIndex(new int[] {0, 0, 0, 0}));
        System.out.println(transitionIndex(new int[] {0, 0, 0, 0, 1}));
        System.out.println(transitionIndex(new int[] {0, 0, 0, 0 ,0, 1, 1, 1}));
        System.out.println(transitionIndex(new int[] {0, 0, 0, 0 ,0, 0, 1, 1, 1}));
        System.out.println(transitionIndex(new int[] {0, 0, 1, 1, 1, 1, 1, 1, 1}));
    }

    static int transitionIndex(int[] arr) {
        if (arr[arr.length -1] == 0)
            return -1;

        int index = arr.length/2;
        while(index < arr.length) {
            if (arr[index] > arr[index -1]) {
                break;
            } else if (arr[index] < arr[index +1]) {
                index++;
                break;
            } else {
                if (arr[index] == 0) {
                    index = index + (arr.length - index)/2;
                } else {
                    index = index/2;
                }
            }
        }

        return index;
    }
}
