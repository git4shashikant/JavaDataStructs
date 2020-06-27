package com.designpatterns.creational;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Example for singleton pattern loading instance lazily with double check for thread safety.
 * 
 * @author shashi1846
 *
 */
public class LazySingletonTest {

	// Instance reference for lazy load
	private static volatile LazySingletonTest lazyInstance;
	private static final ReentrantLock lock = new ReentrantLock();
	
	// private constructor to avoid external instantiation
	private LazySingletonTest(){}
	
	public static LazySingletonTest getInstance() {
		// trying lock to try something other than synchronized
		if (lazyInstance==null){
			try {
				lock.lock();
				System.out.println("acquiring lock.");
				if (lazyInstance==null){
					lazyInstance = new LazySingletonTest();
				}
		     } finally {
		       lock.unlock();
		     }
		}
		return lazyInstance;
	}
	
	public static void main(String ...strings) {
		Thread thread1 = new Thread(){
			public void run() {
					Thread.currentThread().setName("Thread-1");
					LazySingletonTest.getInstance();
			}
		};
		
		Thread thread2 = new Thread() {
			public void run() {
					Thread.currentThread().setName("Thread-2");
					LazySingletonTest.getInstance();
			}
		};
		
		thread1.start();
		thread2.start();
		
	}
	
}