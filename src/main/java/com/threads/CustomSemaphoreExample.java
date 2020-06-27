package com.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomSemaphoreExample {

	private volatile boolean isLocked = false;
	private final AtomicInteger maxAllowedThreads;
	private volatile List<Thread> lockedBy = new ArrayList<>();

	public CustomSemaphoreExample(int maxAllowedThreads) {
		this.maxAllowedThreads = new AtomicInteger(maxAllowedThreads);
	}

	public synchronized void lock() throws InterruptedException{
		Thread callingThread = Thread.currentThread();

		if (lockedBy.contains(callingThread)) {
			System.out.println("Already locked by: " + callingThread.getName());
			return;
		}

		while(lockedBy.size() == maxAllowedThreads.get()){
			System.out.println("Already locked by max number of threads. Wait for: " + callingThread.getName());
			isLocked = true;
			wait();
		}

		System.out.println("Resource available, locking by: " + callingThread);
		lockedBy.add(callingThread);
	}


	public synchronized void unlock(){
		Thread lockingThread = Thread.currentThread();
		if(lockedBy.contains(lockingThread)){
			lockedBy.remove(lockingThread);
			if(lockedBy.size() < maxAllowedThreads.get()){
				isLocked = false;
				System.out.println("Releasing lock by: " + lockingThread);
				notify();
			}
		}
	}

	public void nonSynchronizedMethod() throws InterruptedException {
		lock();
		System.out.println("lock acquired by." + Thread.currentThread());
		Thread.sleep(2000);
		unlock();
	}

	public static void main(String[] args) {
		final CustomSemaphoreExample customSemaphoreExample = new CustomSemaphoreExample(2);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("running thread: " + Thread.currentThread());
				try {
					customSemaphoreExample.nonSynchronizedMethod();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Thread thread1 = new Thread(runnable, "Thread-1");
		Thread thread2 = new Thread(runnable, "Thread-2");
		Thread thread3 = new Thread(runnable, "Thread-3");
		Thread thread4 = new Thread(runnable, "Thread-4");

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
