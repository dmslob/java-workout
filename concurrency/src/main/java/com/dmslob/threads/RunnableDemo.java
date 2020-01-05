package com.dmslob.threads;

public class RunnableDemo {

    public static void main(String[] args) {
        Thread t = new Thread(new Worker(5));
        t.start();

        System.out.println("Waiting ...");
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

    public void run() {
        while (count-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }

    public String status() {
        return "Worker:" + id + " -> (" + (count > 0 ? "count: " + count : "Finished!") + ")  ";
    }
}