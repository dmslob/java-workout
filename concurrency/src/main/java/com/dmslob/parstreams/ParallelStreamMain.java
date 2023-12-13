package com.dmslob.parstreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

final class ParallelStreamMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ParallelStreamMain main = new ParallelStreamMain();
        main.sumInParallel();
        //main.sumInParallel();
        //main.sumInParallel();
    }

    public void sumInParallel() throws InterruptedException, ExecutionException {
        long firstNum = 1;
        long lastNum = 1_000_000;

        List<Long> longs = LongStream.rangeClosed(firstNum, lastNum)
                .boxed().collect(Collectors.toList());

        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        long actualTotal = customThreadPool.submit(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                    return longs.parallelStream()
                            .reduce(0L, Long::sum);
                }
        ).get();
        System.out.println("actualTotal: " + actualTotal);
    }

    void summOfSquares() {
        long sum = new SplittableRandom().ints(100_000_000)
                .parallel()
                .mapToLong(x -> (long) x * x)
                .sum();
        System.out.println("Sum of squares = " + sum);
    }

    void compareStreams() {
        System.out.println("=================================");
        System.out.println("Using Sequential Stream");
        System.out.println("=================================");

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        IntStream intArrStream = Arrays.stream(array);
        intArrStream.forEach(s -> System.out.println(s + " " + Thread.currentThread().getName()));

        System.out.println("=================================");
        System.out.println("Using Parallel Stream");
        System.out.println("=================================");

        IntStream intParallelStream = Arrays.stream(array).parallel();
        intParallelStream.forEach(s -> System.out.println(s + " " + Thread.currentThread().getName()));
    }

    void measure() {
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < 100_000; i++) {
            data.add(i);
        }

        long currentTime = System.currentTimeMillis();
        long sum = data.stream().parallel()
                .map(i -> (int) Math.sqrt(i))
                .map(number -> performComputation(number))
                .reduce(0, Integer::sum);

        long endTime = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println("Time taken to complete:" + (endTime - currentTime) / (1000 * 60) + " minutes");
    }

    int performComputation(int number) {
        int sum = 0;
        for (int i = 1; i < 1000_000; i++) {
            int div = (number / i);
            sum += div;

        }
        return sum;
    }
}
