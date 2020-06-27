package com.datastructure.tree;

/*
* By default its MinHeap, to make it work as a MaxHeap pass reverseOrder as true
*
* */
public class CustomHeap {

    public static void main(String[] args) {
        Heap minHeap = new Heap(10, false);
        minHeap.push(5);
        minHeap.push(6);
        minHeap.push(3);
        minHeap.push(8);
        minHeap.push(2);
        minHeap.push(4);

        System.out.print("Min Heap: ");
        int maxLoop = minHeap.length();
        for(int i = 0; i < maxLoop; i++) {
            System.out.print(minHeap.pop() + " ");
        }

        System.out.println("");

        Heap maxHeap = new Heap(10, true);
        maxHeap.push(5);
        maxHeap.push(6);
        maxHeap.push(3);
        maxHeap.push(8);
        maxHeap.push(2);
        maxHeap.push(4);

        System.out.print("Max Heap: ");
        maxLoop = maxHeap.length();
        for(int i = 0; i < maxLoop; i++) {
            System.out.print(maxHeap.pop() + " ");
        }
    }

    static class Heap {
        private int [] heap;
        private boolean isMaxHeap;
        private int lastIndex;

        private static final int rootIndex = 0;

        public Heap(int size, boolean isMaxHeap) {
            this.heap = new int[size];
            this.isMaxHeap = isMaxHeap;
        }

        public void push(int i) {
            push(i, rootIndex);
        }

        private void push(int i, int index) {
            int leftIndex = (2 * index) + 1;
            int rightIndex = (2 * index) + 2;
            if (heap[index] == 0) {
                heap[index] = i;
                lastIndex = index;
                balanceBottomUp(index);
            } else if (heap[leftIndex] == 0) {
                push(i, leftIndex);
            } else if (heap[rightIndex] == 0) {
                push(i, rightIndex);
            } else {
                push(i, index + 1);
            }
        }

        public int pop() {
            int root = heap[0];
            if (lastIndex == 0) return root;

            int lastElement = heap[lastIndex];
            heap[lastIndex] = 0;
            lastIndex--;
            heap[0] = lastElement;

            balanceTopToBottom(rootIndex);
            return root;
        }

        private void balanceBottomUp(int index) {
            if (index != 0) {
                int parentIndex = (index % 2 == 0) ? (index - 1)/2 : index/2;
                if ((!isMaxHeap && heap[index] < heap[parentIndex]) ||
                        isMaxHeap && heap[index] > heap[parentIndex]) {
                    int temp = heap[parentIndex];
                    heap[parentIndex] = heap[index];
                    heap[index] = temp;
                    balanceBottomUp(parentIndex);
                }
            }
        }

        private int parentOfLastElement() {
            return (lastIndex % 2 == 0) ? (lastIndex - 1)/2 : lastIndex/2;
        }

        private void balanceTopToBottom(int index) {
            if (index <= parentOfLastElement()) {
                int leftIndex = (2 * index) + 1;
                int rightIndex = (2 * index) + 2;

                int maxIndex;
                int minIndex;
                if (heap[leftIndex] < heap[rightIndex]) {
                    minIndex = leftIndex;
                    maxIndex = rightIndex;
                } else {
                    minIndex = rightIndex;
                    maxIndex = leftIndex;
                }

                if (isMaxHeap && heap[index] < heap[maxIndex]) {
                    int temp = heap[maxIndex];
                    heap[maxIndex] = heap[index];
                    heap[index] = temp;
                    balanceTopToBottom(maxIndex);
                } else if (!isMaxHeap && heap[minIndex] != 0 && heap[index] > heap[minIndex]) {
                    int temp = heap[minIndex];
                    heap[minIndex] = heap[index];
                    heap[index] = temp;
                    balanceTopToBottom(minIndex);
                }
            }
        }

        public int length() {
            return lastIndex + 1;
        }
    }
}
