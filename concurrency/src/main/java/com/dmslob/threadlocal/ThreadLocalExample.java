package com.dmslob.threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Task extends Thread {

    private final int id;

    public Task(int idn) {
        id = idn;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            ThreadLocalExample.increment();
            System.out.println("Task " + id + ": " + ThreadLocalExample.get());
            Thread.yield();
        }
    }
}

public class ThreadLocalExample {

    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random rand = new Random(47);

        @Override
        protected synchronized Integer initialValue() {
            return rand.nextInt(10000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 5; i++) {
            new Task(i).start();
        }

        TimeUnit.SECONDS.sleep(3);
    }
}
