package com.dmslob.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinMain {

    private static final int WARM_UP = 100;

    private ForkJoinPool forkJoinPool;

    public ForkJoinMain() {
        forkJoinPool = ForkJoinPool.commonPool();
    }

    public static void main(String[] args) {
        int numItems = 100000000;
        int[] data = new int[numItems];
        Random random = new Random();

        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt();
        }

        ForkJoinMain forkJoinMain = new ForkJoinMain();
        forkJoinMain.doSum(data);
        forkJoinMain.doSumSquares(data);
        forkJoinMain.doMax(data);
        forkJoinMain.doNumPositive(data);
    }

    void warmUp(AbstractExampleTask task) {
        for (int i = 0; i < WARM_UP; i++) {
            forkJoinPool.invoke(task);
        }
    }

    void doSumSquares(int[] data) {
        warmUp(new SumSquaresTask(data, 0, data.length - 1));

        long fjStart = System.currentTimeMillis();
        long fjSum = forkJoinPool.invoke(new SumSquaresTask(data, 0, data.length - 1));
        long fjEnd = System.currentTimeMillis();

        long simpleStart = System.currentTimeMillis();
        long simpleSum = 0L;
        // compute it the simple way
        for (int i = 0; i < data.length; i++) {
            simpleSum = simpleSum + ((long) data[i]) * ((long) data[i]);
        }
        long simpleEnd = System.currentTimeMillis();

        System.out.println("Got " + fjSum + " in " + (fjEnd - fjStart));
        System.out.println("Simple sum of squares " + simpleSum + " in " + (simpleEnd - simpleStart));
    }

    void doNumPositive(int[] data) {
        warmUp(new NumPositiveTask(data, 0, data.length - 1));

        long fjStart = System.currentTimeMillis();
        long fjNumPos = forkJoinPool.invoke(new NumPositiveTask(data, 0, data.length - 1));
        long fjEnd = System.currentTimeMillis();

        long simpleStart = System.currentTimeMillis();
        long simpleNumPos = 0L;

        // compute it the simple way
        for (int i = 0; i < data.length; i++) {
            if (data[i] > 0) {
                simpleNumPos++;
            }
        }
        long simpleEnd = System.currentTimeMillis();

        System.out.println("Got " + fjNumPos + " in " + (fjEnd - fjStart));
        System.out.println("Simple num pos " + simpleNumPos + " in " + (simpleEnd - simpleStart));

    }

    void doMax(int[] data) {
        warmUp(new MaxTask(data, 0, data.length - 1));

        long fjStart = System.currentTimeMillis();
        long fjMax = forkJoinPool.invoke(new MaxTask(data, 0, data.length - 1));
        long fjEnd = System.currentTimeMillis();

        long simpleStart = System.currentTimeMillis();
        long simpleMax = Long.MIN_VALUE;

        // compute it the simple way
        for (int i = 0; i < data.length; i++) {
            simpleMax = data[i] > simpleMax ? data[i] : simpleMax;
        }
        long simpleEnd = System.currentTimeMillis();

        System.out.println("Got " + fjMax + " in " + (fjEnd - fjStart));
        System.out.println("Simple max " + simpleMax + " in " + (simpleEnd - simpleStart));

    }

    void doSum(int[] data) {
        warmUp(new SumTask(data, 0, data.length - 1));

        long fjStart = System.currentTimeMillis();
        long fjSum = forkJoinPool.invoke(new SumTask(data, 0, data.length - 1));
        long fjEnd = System.currentTimeMillis();

        long simpleStart = System.currentTimeMillis();
        long simpleSum = 0L;
        // compute it the simple way
        for (int i = 0; i < data.length; i++) {
            simpleSum = simpleSum + (long) data[i];
        }
        long simpleEnd = System.currentTimeMillis();

        System.out.println("Got " + fjSum + " in " + (fjEnd - fjStart));
        System.out.println("Simple sum " + simpleSum + " in " + (simpleEnd - simpleStart));
    }
}
