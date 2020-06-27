package com.datastructure.hashmap;

import java.util.concurrent.CountDownLatch;

public class CustomConcurrentHashMap {

	private volatile Segment[] segments;
	private static final int size = 16;
	
	public CustomConcurrentHashMap(){
		this.segments = new Segment[size];
	}

	public static void main (String [] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		CustomConcurrentHashMap map = new CustomConcurrentHashMap();
		TestThread thread1 = new TestThread(map, countDownLatch, "key-1", "value-1");
		TestThread thread2 = new TestThread(map, countDownLatch, "key-2", "value-2");
		TestThread thread3 = new TestThread(map, countDownLatch, "key-3", "value-3");

		thread1.start();
		thread2.start();
		thread3.start();

		assert(map.get("key-1") != null);
		assert(map.get("key-2") != null);
		assert(map.get("key-3") != null);

		try {
			countDownLatch.await();
			assert(map.get("key-1") == null);
			assert(map.get("key-2") == null);
			assert(map.get("key-3") == null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void put(Object key, Object value) {
		int segmentLoc = Math.abs(key.toString().length()%16);

		synchronized (segments) {
			if (segments[segmentLoc] == null){
				segments[segmentLoc] = new Segment();
			}
		}

		synchronized (segments[segmentLoc]){
			segments[segmentLoc].getMap().put(key, value);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Object get(Object key) {
		Object returnObj = null;
		int segmentLoc = Math.abs(key.toString().length()%16);
		Segment currentSegment  = segments[segmentLoc];
		if (currentSegment!=null){
			returnObj = currentSegment.getMap().get(key);
		}

		return returnObj;
	}

	public void remove(Object key) {
		int segmentLoc = Math.abs(key.toString().length()%16);
		synchronized (segments[segmentLoc]){
			segments[segmentLoc].getMap().remove(key);
		}

	}

	static class Segment {
		private CustomHashMap map = new CustomHashMap();

		public CustomHashMap getMap() {
			return map;
		}

	}

	static class TestThread extends Thread {

		private final CountDownLatch countDownLatch;
		private final CustomConcurrentHashMap map;
		private Object key;
		private Object value;

		TestThread(CustomConcurrentHashMap map,
				   CountDownLatch countDownLatch,
				   Object key,
				   Object value) {
			this.map = map;
			this.key = key;
			this.value = value;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			try {
				map.put(key, value);
				Thread.sleep(2000);
				map.remove(key);
				countDownLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

