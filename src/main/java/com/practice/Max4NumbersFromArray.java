package com.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Max4NumbersFromArray {

    public static void main(String[] args) {
        int[] array = new int[] {2, 5, 6, 7, 89, 3, 4, 98, 12, 76, 67};

        System.out.println(getNthMaxNumber(array, 4));
        System.out.println(getNthMinNumber(array, 4));
    }

    static int getNthMaxNumber(int[] array, int index) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.stream(array)
                .forEach(i -> {
                    pq.add(i);
                    if (pq.size() > index) {
                        pq.poll();
                    }
                });

        return pq.peek();
    }

    static int getNthMinNumber(int[] array, int index) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Arrays.stream(array)
                .forEach(i -> {
                    pq.add(i);
                    if (pq.size() > index) {
                        pq.poll();
                    }
                });

        return pq.peek();
    }
}
