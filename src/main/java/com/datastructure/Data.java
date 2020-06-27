package com.datastructure;

import java.util.Objects;

public class Data {
	
	private int i;
	private float f;
	private String s;
	
	public Data (int i, float f, String s){
		this.i=i;
		this.f=f;
		this.s=s;
	}
	
	public int getI() {
		return i;
	}

	public float getF() {
		return f;
	}

	public String getS() {
		return s;
	}

	@Override
	public String toString() {
		return "Data [i=" + i + ", f=" + f + ", s=" + s + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// to make hash code same for different float value Data object
		// result = prime * result + Float.floatToIntBits(f);
		result = prime * result + i;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Data)) return false;
		Data data = (Data) o;
		return getI() == data.getI() &&
				Float.compare(data.getF(), getF()) == 0 &&
				Objects.equals(getS(), data.getS());
	}
}
