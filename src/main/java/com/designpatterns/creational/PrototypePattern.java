package com.designpatterns.creational;

public class PrototypePattern {

	public static void main (String args []){
		OuterPrototype op = new OuterPrototype();
		InnerPrototype ip = new InnerPrototype();
		ip.setI(234);
		op.setInner(ip);
		
		Object obj = op.clone();
		System.out.println("");
	}
	
}

class OuterPrototype implements Cloneable{
	
	private InnerPrototype inner;

	public InnerPrototype getInner() {
		return inner;
	}

	public void setInner(InnerPrototype inner) {
		this.inner = inner;
	}

	@Override
	protected Object clone() {
		Object clonedObject = null;
		try {
			clonedObject = super.clone();
		} catch(CloneNotSupportedException e) {
			System.out.println("Exception ocurred while cloning :"+e);
		}
		return clonedObject;
	}
	
}

class InnerPrototype implements Cloneable{
	
	private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@Override
	protected Object clone() {
		Object clonedObject = null;
		try {
			clonedObject = super.clone();
		} catch(CloneNotSupportedException e) {
			System.out.println("Exception ocurred while cloning :"+e);
		}
		return clonedObject;
	}
	
}