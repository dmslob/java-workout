package com.dmslob.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorMain {

    public static void main(String[] args) throws InterruptedException {
        //schedule();
        invokeAll();
    }

    public static void schedule() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Future<String> future = executorService.schedule(() -> {
            // ...
            return "Hello world";
        }, 1, TimeUnit.SECONDS);

        ScheduledFuture<?> scheduledFuture = executorService.schedule(() -> {
            // ...
        }, 1, TimeUnit.SECONDS);

        executorService.shutdown();
    }

    public static void invokeAll() throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3"
        );
        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
    }

}