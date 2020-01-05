package com.dmslob.lock;

/**
 * Created by Dmytro_Slobodenyuk on 6/27/2018.
 */
public class SynchronizedSpaceship implements Spaceship {

    private int x;
    private int y;

    public synchronized int readPosition(final int[] coordinates) {
        coordinates[0] = x;
        coordinates[1] = y;

        return 1;
    }

    public synchronized int move(final int xDelta, final int yDelta) {
        x += xDelta;
        y += yDelta;

        return 1;
    }
}
