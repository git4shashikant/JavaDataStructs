package com.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class showing example of using Executor framework executing Callable ThreadDeadLock to retrieve
 * results in Future Object.
 * 
 * @author shashi1846
 *
 */
public class ExecutingCallable {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
//		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
	    List<Future<String>> list = new ArrayList<Future<String>>();
	    
	    for (int i=0; i<5; i++){
	    	ImplementsCallable call = new ImplementsCallable();
	    	Future<String> future = executor.submit(call);
	    	list.add(future);
	    }
		
	    System.out.println("Tasks submitted.");
	    
    	// method to get all thread stack traces
	    Map<Thread, StackTraceElement[]> m = Thread.getAllStackTraces();
	    for(Map.Entry<Thread,  StackTraceElement[]> e : m.entrySet()) {
	        System.out.println(e.getKey().toString());
	        for (StackTraceElement s : e.getValue()) {
	            System.out.println("  " + s);
	        }
	    }
	    
	    for (Future<String> future : list){
	    	try {
				String name = (String)future.get();
				System.out.println("Executing thread :"+name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	    }
	}

	static class ImplementsCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			Thread.sleep(5000);
			return Thread.currentThread().getName();
		}

	}
}

