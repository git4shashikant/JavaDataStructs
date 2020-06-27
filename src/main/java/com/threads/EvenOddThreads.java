package com.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class EvenOddThreads {

	public static void main (String [] args){
		IndexCounter indexCounter = new IndexCounter();
		Thread oddIndexCounter = new Thread(indexCounter, "OddIndexCounter");
		Thread evenIndexCounter = new Thread(indexCounter, "EvenIndexCounter");

		oddIndexCounter.start();
		evenIndexCounter.start();
	}

	 /**
	 * The effect of the volatile keyword is approximately that each individual read or write operation
	 *  on that variable is atomic. Notably, however, an operation that requires more than one read/write
	 *  -- such as i++, which is equivalent to i = i + 1, which does one read and one write -- is not atomic,
	 *  since another thread may write to i between the read and the write.
	 *  The Atomic classes, like AtomicInteger and AtomicReference, provide a wider variety of operations atomically,
	 *   specifically including increment for AtomicInteger
	 */
	 static class IndexCounter implements Runnable {

		private static volatile AtomicInteger index = new AtomicInteger(0);
		// this also works, atomic operation is not needed as the synchronized
		// keyword will take care of the atomic operation.
		// private static volatile int index = 0;

		@Override
		public void run() {
			synchronized(this) {
				while (index.get()<10) {
					index.incrementAndGet();
					System.out.println("index: "+index.get()+" from "+Thread.currentThread().getName());
					notify();
					try {
						wait();
					} catch (InterruptedException e) {
						System.out.println("interrupted");
					}
				}
			}
		}
	}
}

