package com.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadDeadLockMediator {

	public static void main (String [] args) throws InterruptedException {
		System.out.println("Robert and Martin are 2 persons.");
		PersonWithMediator robert = new PersonWithMediator("Robert");
		PersonWithMediator martin = new PersonWithMediator("Luis");

		System.out.println("Robert and Martin get friends but got disputes.");
		robert.setFriend(martin);
		martin.setFriend(robert);

		System.out.println("Mediator decided to arrange a meeting to resolve the disputes.");
		CountDownLatch donald = new CountDownLatch(2);
		robert.setMediator(donald);
		martin.setMediator(donald);

		System.out.println("Still both were carrying the thoughts in mind.");
		Thread robertThoughts = new Thread(robert);
		Thread martinThoughts = new Thread(martin);

		meetingAfterDisputes(robertThoughts, martinThoughts);

		System.out.println("Mediator waiting for them to respond.");
		donald.await(20, TimeUnit.SECONDS);
		System.out.println(String.format("Mediator breaks the deadlock."));
		robertThoughts.interrupt();
		martinThoughts.interrupt();

		donald.await();
		System.out.println("Mediator and friends are happy :).");
	}

	private static void meetingAfterDisputes(Thread robertThoughts, Thread martinThoughts) {
		System.out.println("Robert and Martin meeting after disputes");
		robertThoughts.start();
		martinThoughts.start();
	}

	static class PersonWithMediator implements Runnable {

		private String name;
		private PersonWithMediator friend;
		private CountDownLatch mediator;
		private boolean shouldOfferGreeting;

		public PersonWithMediator(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setMediator(CountDownLatch mediator) {
			this.mediator = mediator;
		}

		public void setFriend(PersonWithMediator friend) {
			this.friend = friend;
		}

		public boolean isShouldOfferGreeting() {
			return shouldOfferGreeting;
		}

		public void run() {
			shouldOfferGreeting();
			if (shouldOfferGreeting) {
				sayHi();
				mediator.countDown();
			}
		}

		private void sayHi(){
			System.out.println(String.format("%s says: Hi %s.", this.getName(), friend.getName()));
		}

		private void shouldOfferGreeting(){
			System.out.println(String.format("%s thinks should I offer greeting to %s.", getName(), friend.getName()));
			synchronized(friend) {
				while(false == friend.isShouldOfferGreeting()) {
					try {
						friend.wait();
					} catch (InterruptedException e) {
						System.out.println(String.format("Interrupted %s to say Hi to %s",
								getName(), friend.getName()));
						System.out.println(String.format("%s changed his mind and greeted", getName()));
						break;
					}
				}

				shouldOfferGreeting = true;
				friend.notify();
			}
		}
	}
 }