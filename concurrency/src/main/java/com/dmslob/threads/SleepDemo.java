package com.dmslob.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepDemo {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepWorker());
        }
        exec.shutdown();
    }
}

class SleepWorker implements Runnable {

    protected int count = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public SleepWorker() {
    }

    public SleepWorker(int count) {
        this.count = count;
    }

    public void run() {
        try {
            while (count-- > 0) {
                System.out.println(status());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }

    public String status() {
        return "Worker:" + id + " -> (" + (count > 0 ? "count: " + count : "Finished!") + ")  ";
    }
}