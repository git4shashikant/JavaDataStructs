package com.practice;

import java.util.LinkedList;
import java.util.Queue;

public class StackFrom2Queues {

    public static void main (String[] args) {
        CustomStack customStack = new CustomStack();
        customStack.push(1);
        customStack.push(2);
        customStack.push(7);
        customStack.push(16);

        System.out.println(customStack.pull());
        System.out.println(customStack.pull());
        System.out.println(customStack.pull());
    }

    static class CustomStack {
        private Queue<Integer> defaultQueue;
        private Queue<Integer> helperQueue;
        private boolean isCurrentQueueDefault;

        public CustomStack() {
            defaultQueue = new LinkedList();
            helperQueue = new LinkedList();

            isCurrentQueueDefault = true;
        }

        public synchronized void push(int i) {
            if (isCurrentQueueDefault) {
                defaultQueue.add(i);
            } else {
                helperQueue.add(i);
            }
        }

        public synchronized int pull() {
            Queue<Integer> dataQueue;
            Queue<Integer> secondaryQueue;
            if (isCurrentQueueDefault) {
                dataQueue = defaultQueue;
                secondaryQueue = helperQueue;
                isCurrentQueueDefault = false;
                System.out.println("Data fetched from default queue.");
            } else {
                dataQueue = helperQueue;
                secondaryQueue = defaultQueue;
                isCurrentQueueDefault = true;
                System.out.println("Data fetched from helper queue.");
            }

            while (dataQueue.size() > 0 && dataQueue.size() != 1) {
                secondaryQueue.add(dataQueue.poll());
            }

            return dataQueue.poll();
        }
    }
}
