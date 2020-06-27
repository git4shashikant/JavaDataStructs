package com.datastructure.queues;

import com.threads.ProducerConsumerProb;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Example of Blocking Queue: Wait for the queue to get empty before adding an element and 
 * wait for the queue to get filled to get.
 * 
 * @author shashi1846
 *
 */
public class BlockingQueue {

	private volatile LinkedList<Object> queue = new LinkedList<Object>();
	private static int limit = 10;

	public BlockingQueue(int limit){
		this.limit = limit;
	}

	public synchronized void enqueue(Object item) throws InterruptedException {
		// put on wait if queue is full
		if (this.queue.size() == this.limit) {
			System.out.println("going to wait as queue is not empty.");
			wait();

		}

		System.out.println("Enqueued: " + item);
		this.queue.add(item);
		notifyAll();
	}

	public synchronized Object dequeue() throws InterruptedException{
		// wait if queue is empty
		if(this.queue.size() == 0){
			wait();
		}

		System.out.println("removed from queue: " + queue.getFirst());
		notifyAll();
		return this.queue.pop();
	}
	
	public static void main (String args []){
		
		BlockingQueue bq = new BlockingQueue(2);
		Thread producer = new Thread(new Producer(bq));
		Thread consumer = new Thread(new Consumer(bq));

		producer.start();
		consumer.start();
	}

	static class Producer implements Runnable{

		private BlockingQueue queue;

		public Producer(BlockingQueue queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			for (int index = 0; index < 10; index++) {
				try {
					Thread.sleep(500);
					queue.enqueue(index);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Consumer implements Runnable{

		private BlockingQueue queue;

		public Consumer(BlockingQueue queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
					queue.dequeue();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

