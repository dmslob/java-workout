package com.dmslob.synchronizer.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class TaskDemo {

    public static void main(String[] args) {

        final Random rand = new Random();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            new Task(semaphore, rand.nextInt(5)).start();
        }
    }
}

class Task extends Thread {

    private static int counter = 0;
    private final int id = counter++;
    private Semaphore semaphore;
    private int time;

    Task(Semaphore s, int t) {
        semaphore = s;
        time = t;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            System.out.println("Task " + id + " duration for " + time + " seconds");
            Thread.sleep(time * 1000);
            System.out.println("Task " + id + " done");

            semaphore.release();
        } catch (InterruptedException e) {
            System.out.println("Task interrupted");
        }
    }
}