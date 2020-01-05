package com.dmslob.diningphilosophers;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@NotThreadSafe
public abstract class AbstractPhilosopher<T extends Chopstick> implements Runnable {

    @SuppressWarnings({"UnsecureRandomNumberGeneration"})
    private final Random random = new Random();

    private static final int MAX_SLEEP = 100;
    private static final int MIN_SLEEP = 10;

    protected T left;
    protected T right;

    private final int maxEats;

    private final CountDownLatch latch;

    protected AbstractPhilosopher(@Nonnull CountDownLatch latch, @Nullable T left, @Nullable T right, int maxEats) {
        this.latch = latch;
        this.left = left;
        this.right = right;
        this.maxEats = maxEats;
    }

    @Override
    public void run() {
        try {
            this.latch.countDown();
            this.latch.await();

            int mealsEaten = 0;
            while (mealsEaten < this.maxEats) {
                randomSleep();
                eat(mealsEaten);
                mealsEaten++;
            }
            System.out.println("Eaten enough");
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted, exiting");
        }
    }

    protected abstract void eat(int mealsEaten) throws InterruptedException;

    /**
     * @throws InterruptedException if interrupted while sleeping
     */
    protected void randomSleep() throws InterruptedException {
        int sleep = random.nextInt(MAX_SLEEP - MIN_SLEEP + 1) + MIN_SLEEP;
        Thread.sleep(sleep);
    }
}
