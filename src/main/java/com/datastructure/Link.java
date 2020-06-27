package com.datastructure;

public class Link {
	
	private Data data;
	private Link next;
	private Link previous;
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public Link getNext() {
		return next;
	}
	public void setNext(Link next) {
		this.next = next;
	}
	public Link getPrevious() {
		return previous;
	}
	public void setPrevious(Link previous) {
		this.previous = previous;
	}
}
