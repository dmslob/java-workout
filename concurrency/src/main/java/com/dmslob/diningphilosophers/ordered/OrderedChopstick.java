package com.dmslob.diningphilosophers.ordered;

import com.dmslob.diningphilosophers.Chopstick;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
final class OrderedChopstick implements Chopstick {

    private final Lock lock = new ReentrantLock();

    private static final AtomicInteger NUM_INSTANCES = new AtomicInteger();

    private final int instanceNum = NUM_INSTANCES.incrementAndGet();

    boolean isBefore(OrderedChopstick other) {
        if (this.instanceNum == other.instanceNum) {
            throw new IllegalArgumentException();
        }

        return this.instanceNum < other.instanceNum;
    }

    @SuppressWarnings({"LockAcquiredButNotSafelyReleased"})
    @Override
    public void acquireOwnership() throws InterruptedException {
        lock.lockInterruptibly();
    }

    @Override
    public void releaseOwnership() {
        lock.unlock();
    }

}
