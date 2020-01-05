package com.dmslob.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    public static void main(String[] args) {

        Inc inc = new Inc();
        new TestThread(inc).start();
        new IncThread(inc).start();
    }
}

class Inc {
    int i = 0;
    boolean cancel = false;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    void inc() {
        lock.lock();
        try {
            i++;
            System.out.println("signal...");
            cond.signalAll();
        } finally {
            lock.unlock();
        }
    }

    void isFive() {
        lock.lock();
        try {
            while (i < 5) {
                System.out.println("wait...");
                cond.await();
            }
        } catch (InterruptedException e) {
            System.out.println("Iterrupted");
        } finally {
            System.out.println("Five");
            cancel = true;
            lock.unlock();
        }
    }
}

class IncThread extends Thread {

    Inc inc;

    IncThread(Inc _inc) {
        inc = _inc;
    }

    @Override
    public void run() {
        while (true) {
            if (inc.cancel) {
                System.out.println(inc.i);
                break;
            }
            inc.inc();
        }
    }
}

class TestThread extends Thread {

    Inc test;

    TestThread(Inc _test) {
        test = _test;
    }

    @Override
    public void run() {
        test.isFive();
        System.out.println("Done");
    }
}