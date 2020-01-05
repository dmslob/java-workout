package com.dmslob.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public static void main(String[] args) {
        Inc inc = new Inc();
        new MyThread(inc).start();
        new MyThread(inc).start();
    }
}

class Inc {

    int i = 0;
    boolean cancel = false;
    private Lock locker = new ReentrantLock();

    void inc() {
        locker.lock();
        try {
            i++;
            Thread.yield();
            i++;
            Thread.sleep(10);

            if (i % 2 != 0) {
                cancel = true;
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        } finally {
            locker.unlock();
        }
    }
}

class MyThread extends Thread {

    Inc inc;

    MyThread(Inc _inc) {
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
