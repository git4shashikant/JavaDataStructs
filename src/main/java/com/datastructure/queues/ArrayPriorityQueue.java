package com.datastructure.queues;

public class ArrayPriorityQueue {

	private int [] arr;
	private int size = 2;
	private int index = -1;
	
	public ArrayPriorityQueue () {
		arr = new int [size];
	}
	
	public void push (int value) {
		
		if (++index>size-1) {
			size = size*2;
			int [] intArr = new int[size];
			for (int i=0; i<arr.length; i++){
				intArr[i]=arr[i];
			}
			arr = intArr;
		}
		
		arr[index] = value;
		
		int i = index;
		while (i>0){
			if (arr[i]<arr[i-1]) {
				int j = arr[i-1];
				arr[i-1]=arr[i];
				arr[i]=j;
			}
			i--;
		}
	}
	
	public void pop() {
		arr[0] = 0;
		for (int i =0; i<index; i++) {
			arr[i]=arr[i+1];
		}
		arr[index--] = 0;
	}
	
	public static void main (String args []){
		
		ArrayPriorityQueue priorityQueue = new ArrayPriorityQueue();
		priorityQueue.push(1);
		priorityQueue.push(5);
		priorityQueue.push(3);
		priorityQueue.push(2);
		priorityQueue.push(4);
		
		priorityQueue.pop();
		
		System.out.println(""+ priorityQueue);
		
	}
}
