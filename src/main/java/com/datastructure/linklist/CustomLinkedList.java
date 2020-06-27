package com.datastructure.linklist;

import com.datastructure.Data;
import com.datastructure.Link;

public class CustomLinkedList {

	private Link firstLink;

	public Link getFirstLink() {
		return firstLink;
	}

	public void insertAsFirstLink(Link link){
		Link firstLink = this.getFirstLink();
		if (firstLink!=null){
			firstLink.setPrevious(link);
		}
		link.setNext(firstLink);
		this.firstLink = link;
	}

	public void insetAsLastLink(Link link){
		
		Link currLink = firstLink;
		Link lastLink = null;
		
		if (currLink!=null){
			while(currLink.getNext()!=null){
				currLink = currLink.getNext();
			}
			lastLink = currLink;
			link.setPrevious(lastLink);
			lastLink.setNext(link);
			
		}else {
			firstLink = link;
		}
	}
	
	// To be used for Queue pop-up FIFO
	public Link removeFirstLink(){
		if (firstLink!=null){
			firstLink = firstLink.getNext();
			firstLink.setPrevious(null);
		}
		return firstLink;
	}
	
	// To be used for Stack pop-up LIFO
	public Link removeLastLink(){
		
		Link currLink = firstLink;
		Link lastLink = null;
		
		if (currLink!=null){
			while(currLink.getNext()!=null){
				currLink = currLink.getNext();
			}
			lastLink = currLink;
			Link secondLastLink = lastLink.getPrevious();
			lastLink.setPrevious(null);
			secondLastLink.setNext(null);
			return lastLink;
		} 
		return null;
	}
	
	public static void main(String... args){
		
		CustomLinkedList customLinkedList = new CustomLinkedList();
		
		Data data1 = new Data(1, 1.1f, "strign1");
		Data data2 = new Data(2, 1.2f, "strign2");
		Data data3 = new Data(3, 1.3f, "strign3");
		
		Link link1 = new Link();
		link1.setData(data1);
		
		Link link2 = new Link();
		link2.setData(data2);
		
		Link link3 = new Link();
		link3.setData(data3);
		
		customLinkedList.insertAsFirstLink(link2);
		customLinkedList.insetAsLastLink(link3);
		customLinkedList.insertAsFirstLink(link1);
		
		Link start = customLinkedList.getFirstLink();
		if (start!=null){
			System.out.println(start.getData().toString());
			while (start.getNext()!=null){
				start = start.getNext();
				System.out.println(start.getData().toString());
			}
			
			System.out.println("------------------------------------");
			System.out.println(start.getData().toString());
			while (start.getPrevious()!=null){
				start = start.getPrevious();
				System.out.println(start.getData().toString());
			}
		}
	}
}
