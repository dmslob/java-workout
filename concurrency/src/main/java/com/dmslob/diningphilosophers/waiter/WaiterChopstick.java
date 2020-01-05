package com.dmslob.diningphilosophers.waiter;

import com.dmslob.diningphilosophers.Chopstick;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class WaiterChopstick implements Chopstick {

    private final Lock lock = new ReentrantLock();

    @Override
    public void acquireOwnership() throws InterruptedException {
        if (!lock.tryLock()) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void releaseOwnership() {
        lock.unlock();
    }
}
