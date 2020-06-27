package com.datastructure.queues;

public class ArrayQueue {

	private Object [] arr;
	private int size = 2;
	private int index = -1;
	
	public ArrayQueue () {
		arr = new Object [size];
	}
	
	public void push (Object obj) {
		
		if (++index>size-1) {
			size = size*2;
			Object [] ObjArr = new Object[size];
			for (int i=0; i<arr.length; i++){
				ObjArr[i]=arr[i];
			}
			arr = ObjArr;
		}
		
		arr[index] = obj;
	}
	
	public void pop() {
		arr[0] = null;
		for (int i =0; i<index; i++) {
			arr[i]=arr[i+1];
		}
		arr[index--] = null;
	}
	
	public static void main (String args []){
		
		ArrayQueue queue = new ArrayQueue();
		queue.push("1");
		queue.push("2");
		queue.push("3");
		queue.push("4");
		queue.push("5");
		
		queue.pop();
		
		System.out.println(""+ queue);
		
	}
	
}
