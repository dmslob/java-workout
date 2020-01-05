package com.dmslob.adapter.canonical;

import java.util.concurrent.atomic.AtomicInteger;

public class CharCounterTask implements Runnable {

    private final String str;
    private final AtomicInteger counter;

    public CharCounterTask(String str, AtomicInteger counter) {
        this.str = str;
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.addAndGet(str.length());
    }
}
