package com.threads;

public class ThreadWaitNotifyExample {

	private static final Printer printer = new Printer();

	public static void main (String [] args){
		long startTime = System.currentTimeMillis();
		WaitNotifyThread t1= new WaitNotifyThread("Thread1", printer, startTime);
		WaitNotifyThread t2= new WaitNotifyThread("Thread2", printer, startTime);

		t1.start();
		t2.start();
	}

	static class WaitNotifyThread extends Thread {

		private static final long timeToRun = 5000;
		private String threadName;
		private Printer printer;
		private long startTime;

		WaitNotifyThread(String threadName,
						 Printer printer,
						 long startTime){
			this.threadName = threadName;
			this.printer = printer;
			this.startTime = startTime;
		}

		public void run(){
			synchronized(printer){
				while(true){
					try {
						long runSecs = System.currentTimeMillis() - startTime;
						if (runSecs < timeToRun) {
							printer.print(threadName + " running");
							printer.notify();
							printer.wait();
						} else {
							printer.notifyAll();
							break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	static class Printer {
		public void print(String s){
			System.out.println(""+s);
		}
	}
}