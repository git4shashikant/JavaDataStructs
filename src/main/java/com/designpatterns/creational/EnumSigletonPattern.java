package com.designpatterns.creational;

public class EnumSigletonPattern {

	public static void main (String ... strings) {
		Singleton.INSTANCE.doStuff();
	}

	static enum Singleton {
		INSTANCE;
		public void doStuff(){
			System.out.println("singleton method call.");
		}
	}
}


