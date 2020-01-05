package com.dmslob.synchronizer.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        PrintQueue queue = new PrintQueue(semaphore);
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new Job(queue), "Thread:" + i);
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
    }
}

class Job implements Runnable {

    private PrintQueue queue;

    public Job(PrintQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " is going to print.");
        queue.printJob(new Object());
        System.out.println(threadName + " document is printed.");
    }
}

class PrintQueue {

    private final Semaphore semaphore;

    public PrintQueue(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void printJob(Object document) {
        try {
            semaphore.acquire();
            long duration = (long) (Math.random() * 1000);
            System.out.println(Thread.currentThread().getName() + " is printing job for " + "duration: " + duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}