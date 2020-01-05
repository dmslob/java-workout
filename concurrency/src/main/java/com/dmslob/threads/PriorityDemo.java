package com.dmslob.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PriorityDemo {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            exec.execute(new Worker(Thread.MIN_PRIORITY));
        }
        exec.execute(new Worker(Thread.NORM_PRIORITY));
        exec.execute(new Worker(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}

class PriorityWorker implements Runnable {

    protected int count = 5;
    private static int taskCount = 0;
    private final int id = taskCount++;
    private int priority;
    private volatile double d;

    public PriorityWorker() {
    }

    public PriorityWorker(int priority) {
        this.priority = priority;
    }

    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--count == 0) {
                return;
            }
        }

    }

    public String toString() {
        return Thread.currentThread() + ": " + count + " -> " + priority;
    }
}