package com.dmslob.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {

    public static void main(String[] args) {

        // Cached Tread Pool
        ExecutorService execCached = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            execCached.execute(new Worker(5));
        }
        execCached.shutdown();

        // Fixed Tread Pool
        ExecutorService execFixed = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            execFixed.execute(new Worker(5));
        }
        execFixed.shutdown();

        // Single Tread Executor
        ExecutorService execSingle = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            execSingle.execute(new Worker(5));
        }
        execSingle.shutdown();
    }
}

class Worker implements Runnable {

    protected int count = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public Worker() {
    }

    public Worker(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (count-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }

    public String status() {
        return "Worker:" + id + " -> (" + (count > 0 ? "count: " + count : "Finished!") + ")";
    }
}