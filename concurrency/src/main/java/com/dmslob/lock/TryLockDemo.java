package com.dmslob.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {

    public static void main(String[] args) {

        NewInc inc = new NewInc();
        new NewThread(inc).start();
        new NewThread(inc).start();
    }
}

class NewInc {

    int i = 0;
    boolean cancel = false;
    private Lock locker = new ReentrantLock();

    void inc() {
        while (!locker.tryLock()) {
            Thread.yield();
        }
        try {
            i++;
            Thread.sleep(100);
            i++;
        } catch (Exception ex) {
            System.out.println("Interrupt");
        } finally {
            if (i % 2 != 0) {
                cancel = true;
            }
            locker.unlock();
        }
    }
}

class NewThread extends Thread {

    NewInc inc;

    NewThread(NewInc _inc) {
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