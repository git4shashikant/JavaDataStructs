package com.threads;

import java.lang.Thread.UncaughtExceptionHandler;

public class CatchingUnhandledThreadException {

	public static void main (String args []){
		MyCustomThreadGroup gp = new MyCustomThreadGroup("ThreadGroup");
		Runnable runnable = new TestUnhandledThreadException();
		Thread t1 = new Thread(runnable, "Thread-1");
		Thread t2 = new Thread(runnable, "Thread-2");
		Thread t3 = new Thread(gp, runnable, "Thread-3");
		
		// thread interruption handled by UncaughtExceptionHandler.
		t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("thread interruption for "+Thread.currentThread().getName()+" is handled by UncaughtExceptionHandler.");
			}
		});

		t1.start();
		t2.start();
		t3.start();
	}

	static class TestUnhandledThreadException implements Runnable{

		static {
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.out.println("thread interruption for "+Thread.currentThread().getName()+" is handled by DefaultUncaughtExceptionHandler.");
				}
			});
		}
			public void run (){
				try {
					Thread.sleep(2000);
					int i = 100/0;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}

	static class MyCustomThreadGroup extends ThreadGroup {

		public MyCustomThreadGroup(String name) {
			super(name);
		}

		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("thread interruption for "+Thread.currentThread().getName()+" is handled by ThreadGroup uncaughtException() method.");
		}
	}
}

