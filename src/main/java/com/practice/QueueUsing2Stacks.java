package com.practice;

import java.util.Stack;

public class QueueUsing2Stacks {

    public static void main(String[] args) {
        CustomQueue customQueue = new CustomQueue();
        customQueue.push(1);
        customQueue.push(2);
        customQueue.push(7);
        customQueue.push(16);

        System.out.println(customQueue.pull());
        System.out.println(customQueue.pull());
        System.out.println(customQueue.pull());
    }

    static class CustomQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> pullStack;

        public CustomQueue() {
            pushStack = new Stack();
            pullStack = new Stack();
        }

        public synchronized void push(int i) {
            pushStack.push(i);
        }

        public synchronized int pull() {
            if (pullStack.size() == 0) {
                while(pushStack.size() > 0) {
                    pullStack.push(pushStack.pop());
                }
            }

            return pullStack.pop();
        }
    }
}
