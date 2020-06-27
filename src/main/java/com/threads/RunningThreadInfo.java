package com.threads;

import java.util.Map;

public class RunningThreadInfo {

	public static void main (String args []) {
		TestRunningThread t1 = new TestRunningThread("Thread-1");
		TestRunningThread t2 = new TestRunningThread("Thread-2"); 
		TestRunningThread t3 = new TestRunningThread("Thread-3"); 
		TestRunningThread t4 = new TestRunningThread("Thread-4"); 
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		// method to find currently running thread using stack trace
		System.out.println("Running thread info using stacktrace");
		System.out.println("------------------------------------");
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
		for (Thread t : map.keySet()) {
			System.out.println("Currently running thread :"+t.getName());
		}
		
		// method to find currently running thread using thread group
		System.out.println("Running thread info using ThreadGroup");
		System.out.println("------------------------------------");
		ThreadGroup threadGrp = Thread.currentThread().getThreadGroup();
		threadGrp.list();
	}

	static class TestRunningThread extends Thread {

		public TestRunningThread(String name) {
			super();
			this.setName(name);
		}

		public void run (){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}