package com.dmslob.race;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeReadModifyWriteAtomic {

    private final AtomicInteger number = new AtomicInteger();

    public void incrementNumber() {
        number.getAndIncrement();
    }

    public int getNumber() {
        return this.number.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final SafeReadModifyWriteAtomic rmw = new SafeReadModifyWriteAtomic();

        for (int i = 0; i < 1_000; i++) {
            new Thread(() -> rmw.incrementNumber(), "T" + i).start();
        }

        Thread.sleep(4000);
        System.out.println("Final number (should be 1_000): " + rmw.getNumber());
    }
}
