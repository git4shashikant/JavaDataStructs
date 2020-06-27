package com.threads;

import java.util.concurrent.Semaphore;

/*
* Mutex is short form of mutually exclusive.
* A Semaphore with an access count of 1 is a Mutex.
* ReentrantLock is also Mutex.
* */
public class SemaphoreExample {

	/* creating a semaphore object to allow only one thread of execution */
	private static final Semaphore semaphore = new Semaphore(1);
	
	public void testSemaphore(){
		try {
			semaphore.acquire();
			/* logic you want to protect should be written here */ 
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
	
	public static void main (String ... args) {
		SemaphoreExample lockUse = new SemaphoreExample();
		LockingThread lockUseThreadRunnable = new LockingThread(lockUse);
		Thread thread1 = new Thread(lockUseThreadRunnable, "thread-1");
		Thread thread2 = new Thread(lockUseThreadRunnable, "thread-2");
		thread1.start();
		thread2.start();
	}

	/* thread trying to acquire resource */
	static class LockingThread implements Runnable {

		private SemaphoreExample semaphoreExample;

		LockingThread(SemaphoreExample lockUse) {
			this.semaphoreExample = lockUse;
		}

		@Override
		public void run() {
			semaphoreExample.testSemaphore();
		}
	}
}

