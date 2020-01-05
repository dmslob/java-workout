package com.dmslob.base;

public class CurrentThreadExample {

    public static void main(String[] args) {

        Thread currentThread = Thread.currentThread();
        currentThread.setName("My Thread");
        System.out.println(" " + "current thread: " + currentThread);

        try {
            for (int n = 5; n > 0; n--) {
                System.out.println(" " + n);
                System.out.println(" " + "Waiting 2 seconds...");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}
