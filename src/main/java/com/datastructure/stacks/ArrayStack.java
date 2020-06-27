package com.datastructure.stacks;

public class ArrayStack {

	private Object [] arr;
	private int size = 2;
	private int index = -1;
	
	public ArrayStack () {
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
		arr[index--] = null;
	}
	
	public static void main (String args []){
		
		ArrayStack stack = new ArrayStack();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		stack.push("5");
		
		stack.pop();
		
		System.out.println(""+ stack);
		
	}
	
}
