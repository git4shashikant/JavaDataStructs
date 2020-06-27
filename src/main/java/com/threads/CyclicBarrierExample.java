package com.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String args []) {

        ConcludingThread concludingThread = new ConcludingThread();
        // The second argument to the constructor is a Runnable instance.
        // ConcludingThread logic would be run by the last thread that trips the barrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, concludingThread);

        TestThread thread = new TestThread(cyclicBarrier);

        Thread thread1 = new Thread(thread, "thread-1");
        Thread thread2 = new Thread(thread, "thread-2");
        thread1.start();
        thread2.start();

        // Resets the barrier to be used again
        cyclicBarrier.reset();
    }
    static class TestThread implements Runnable {

        private CyclicBarrier cyclicBarrier;

        public TestThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(String.format("running thread: %s", Thread.currentThread().getName()));

                Thread.sleep(2000);
                // By calling await on cyclic barrier thread trips the barrier
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class ConcludingThread implements Runnable {

        @Override
        public void run() {
            System.out.println(String.format("running concluding thread: %s", Thread.currentThread().getName()));
        }
    }
}


