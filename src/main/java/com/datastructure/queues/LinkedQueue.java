package com.datastructure.queues;

import com.datastructure.Data;
import com.datastructure.Link;
import com.datastructure.linklist.CustomLinkedList;

public class LinkedQueue {

	private CustomLinkedList customLinkedList;
	
	public LinkedQueue () {
		customLinkedList = new CustomLinkedList();
	}
	
	public void push(Link link) {
		customLinkedList.insetAsLastLink(link);
	}
	
	public Link pop() {
		return customLinkedList.removeFirstLink();
	}
	
	public static void main(String [] args) {
		
		LinkedQueue linkedQueue = new LinkedQueue();
		
		Data data1 = new Data(1, 1.1f, "strign1");
		Data data2 = new Data(2, 1.2f, "strign2");
		Data data3 = new Data(3, 1.3f, "strign3");
		
		Link link1 = new Link();
		link1.setData(data1);
		
		Link link2 = new Link();
		link2.setData(data2);
		
		Link link3 = new Link();
		link3.setData(data3);
		
		linkedQueue.push(link1);
		linkedQueue.push(link2);
		linkedQueue.push(link3);
		
		linkedQueue.pop();
		
		System.out.println(""+linkedQueue);
	}
}
