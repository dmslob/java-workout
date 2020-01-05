package com.dmslob.deadlock;

public class DeadLockNow {

    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public static void printHello() {
        synchronized (LOCK_1) {
            System.out.println(Thread.currentThread());
            synchronized (LOCK_2) {
                System.out.println("hi");
            }
        }
    }

    public static void printGoodBye() {
        synchronized (LOCK_2) {
            System.out.println(Thread.currentThread());
            synchronized (LOCK_1) {
                System.out.println("good bye");
            }
        }
    }
}
