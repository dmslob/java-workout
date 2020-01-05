package com.dmslob.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Dmytro_Slobodenyuk on 6/27/2018.
 */
public class ReadWriteLockSpaceShip implements Spaceship {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private int x;
    private int y;

    public int readPosition(final int[] coordinates) {
        readLock.lock();
        try {
            coordinates[0] = x;
            coordinates[1] = y;
        } finally {
            readLock.unlock();
        }

        return 1;
    }

    public int move(final int xDelta, final int yDelta) {
        writeLock.lock();
        try {
            x += xDelta;
            y += yDelta;
        } finally {
            writeLock.unlock();
        }

        return 1;
    }
}
