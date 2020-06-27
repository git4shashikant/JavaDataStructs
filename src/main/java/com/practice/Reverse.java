package com.practice;

import java.security.CodeSource;

public class Reverse{
	
	public static void main (String [] args){
		
		int [] arr = {1, 2, 3, 4, 5};
		reverseArray(arr);

		for (int i=0; i<arr.length; i++){
			System.out.println("" + arr[i]);
		}
	}

	public static void reverseArray(int[] arr) {
		reverse(arr, 0, arr.length-1);
	}
	
	private static void reverse(int[] arr, int i, int j){
		if (i < j){
			int num = arr[i];
			arr[i] = arr[j];
			arr[j] = num;
			i++; j--;
			reverse(arr, i, j);
		}
	}
}
