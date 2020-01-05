package com.dmslob.base;

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("run new thread from main...");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run new thread from daemon...");

                Thread currentThread = Thread.currentThread();

                System.out.println("isDaemon: " + currentThread.isDaemon());
                System.out.println("New thread name: " + currentThread.getName());
                System.out.println("isAlive: " + currentThread.isAlive());
                System.out.println("State: " + currentThread.getState());
                System.out.println("ThreadGroup: " + currentThread.getThreadGroup().getName());
            }
        };

        Thread newThread = new Thread(runnable);
        newThread.setDaemon(false);
        newThread.start();
    }
}

public class RunnableExample {

    public static void main(String[] args) {

        Thread newThread = new Thread(new MyRunnable());

        newThread.setDaemon(true);
        newThread.start();

        System.out.println("isDaemon: " + newThread.isDaemon());
        System.out.println("isAlive: " + newThread.isAlive());
        System.out.println("New thread name: " + newThread.getName());
        System.out.println("State: " + newThread.getState());
        System.out.println("ThreadGroup: " + newThread.getThreadGroup().getName());

        // некоторое долгое действие, вычисление
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}

