package com.dmslob.decorator.canonical;

public class BenchmarkingDigitCounter implements DigitCounter {

    private final DigitCounter delegate;

    public BenchmarkingDigitCounter(DigitCounter delegate) {
        this.delegate = delegate;
    }

    public int count(String str) {
        long startTime = System.currentTimeMillis();
        int count = delegate.count(str);
        long endTime = System.currentTimeMillis();
        System.out.println("Counting took " + (startTime - endTime) + " ms");
        return count;
    }
}
