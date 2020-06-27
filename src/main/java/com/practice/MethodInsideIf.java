package com.practice;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MethodInsideIf{

	public static void main(String [] args){
			
		int[] obj =  new int[10];
		System.out.println(obj[0]);
		if (Test.getBoolean(Executors.newFixedThreadPool(2).submit(new Callable<Object>() {
		   public Object call() {
			   System.out.println("method executed");
				return null;
			  }
			}) instanceof Future)){
			System.out.println("true");
		}
	}

	static class Test{
		public static boolean getBoolean(boolean b){
			return true;
		}
	}
}

