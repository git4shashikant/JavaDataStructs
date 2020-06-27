package com.threads;

import com.datastructure.queues.BlockingQueue;

public class ProducerConsumerProb {

	private static volatile BlockingQueue queue = new BlockingQueue(5); 
	
	public static void main (String args[]){
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		producer.start();
		consumer.start();
	}

	static class Producer extends Thread {

		private BlockingQueue queue;

		Producer (BlockingQueue queue){
			this.queue = queue;
		}

		public void produce() throws InterruptedException{
			for (int index=0; index<20; index++){
				Thread.sleep(1000);
				queue.enqueue(index);
				System.out.println("adding :"+index);
			}
		}
		public void run (){
			try {
				produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class Consumer extends Thread {

		private BlockingQueue queue;

		Consumer(BlockingQueue queue){
			this.queue = queue;
		}

		public void consume() throws InterruptedException{
			while (true){
				Thread.sleep(2000);
				System.out.println("fetching :"+queue.dequeue().toString());
			}
		}

		public void run(){
			try {
				consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

