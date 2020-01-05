package com.dmslob.lock;

public class SyncClass {

    public synchronized void one() {
        System.out.println("One is sync runing");
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one method is end");
    }

    public synchronized void two() {
        System.out.println("two sync is running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();
    }
}
