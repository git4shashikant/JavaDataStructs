package com.threads;

public class ThreadInterruption {

	public static void main (String [] args){
		SleepyThread thread = new SleepyThread();
		thread.start();
		thread.interrupt();
	}

	static class SleepyThread extends Thread {

		public void run() {
			try {
				System.out.println("SleepyThread running.");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("SleepyThread got interrupted.");

				if (this.isInterrupted() || Thread.interrupted()){
					System.out.println("won't print as interrupted status is reset once caught.");
				}
			}
		}
	}
}

