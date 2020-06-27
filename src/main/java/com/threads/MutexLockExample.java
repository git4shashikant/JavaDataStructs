package com.threads;

/**
 * Example of a normal lock mechanism to lock the critical code in multi-thread environment.
 * While locking a resource make sure to surround it with try catch and unlock in finally to 
 * avoid thread lock in exception.
 * 
 * @author shashi1846
 *
 */
public class MutexLockExample {

	private static volatile boolean isLocked = false;

	public synchronized void lock() throws InterruptedException{
		while(isLocked){
			wait();
		}
		isLocked = true;
	}

	public synchronized void unlock(){
		isLocked = false;
		notify();
	}
	
	public static void main (String [] args){
		
		TestThread ttRunnable =  new TestThread();
		Thread t1 = new Thread(ttRunnable, "Thread-1");
		Thread t2 = new Thread(ttRunnable, "Thread-2");
		
		t1.start();
		t2.start();
	}

	/**
	 * TestThread runnable to access TestClass method.
	 * @author shashi1846
	 *
	 */
	static class TestThread implements Runnable {

		private TestClass testClass = new TestClass();

		@Override
		public void run() {
			try {
				testClass.testMethod();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Test class whose method will be accessed by two threads on the same instance, to check locking.
	 * @author shashi1846
	 */
	static class TestClass {

		private volatile MutexLockExample mutexLockExample = new MutexLockExample();

		public void testMethod() throws InterruptedException{

			System.out.println("going to lock the resource");
			mutexLockExample.lock();

			System.out.println("test method called by "+Thread.currentThread().getName());
			Thread.sleep(5000);

			System.out.println("going to release the resource by "+Thread.currentThread().getName());
			mutexLockExample.unlock();

		}
	}
}