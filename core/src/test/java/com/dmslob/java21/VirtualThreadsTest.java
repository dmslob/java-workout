package com.dmslob.java21;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class VirtualThreadsTest {
    @Test
    public void should_test_runnable() throws InterruptedException {
        // given
        System.out.println("Start Virtual thread");

        Runnable task_1 = () -> {
            System.out.println("Running task_1");
            System.out.println(STR."Hello from thread! Thread ID: \{
                    Thread.currentThread().threadId()}");
            System.out.println(STR."Thread isDaemon: \{Thread.currentThread().isDaemon()}");
            System.out.println(STR."Thread Name: \{Thread.currentThread().getName()}");
            System.out.println(STR."Thread State: \{Thread.currentThread().getState()}");
            System.out.println(STR."Thread isVirtual: \{Thread.currentThread().isVirtual()}");
            System.out.println(STR."Thread classloader: \{Thread.currentThread().getContextClassLoader().getName()}");
            System.out.println();
        };
        Thread virtualThread = Thread.ofVirtual().name("my-worker").start(task_1);
        //Thread virtualThread = Thread.ofVirtual().start(task_1);
        virtualThread.join(); // Wait for the virtual thread to complete

        System.out.println("Start Platform thread");
        Thread platformThread = Thread.ofPlatform().start(task_1);
        platformThread.join(); // Wait for the virtual thread to complete
        System.out.println("Test thread finished.");
    }

    @Test
    public void should_test_newVirtualThreadPerTaskExecutor() throws ExecutionException, InterruptedException {
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<?> future = executorService.submit(() -> {
                System.out.println(STR."Thread ID: \{Thread.currentThread().threadId()}");
                System.out.println(STR."Thread isDaemon: \{Thread.currentThread().isDaemon()}");
                System.out.println(STR."Thread Name: \{Thread.currentThread().getName()}");
                System.out.println(STR."Thread State: \{Thread.currentThread().getState()}");
                System.out.println(STR."Thread isVirtual: \{Thread.currentThread().isVirtual()}");
                System.out.println(STR."Thread classloader: \{Thread.currentThread().getContextClassLoader().getName()}");
                System.out.println();
            });
            future.get(); // Wait for the task to complete
        }
    }

    @Test
    public void should_test_newThreadPerTaskExecutor() {
        var threadFactory = Thread.ofVirtual().name("worker-", 1).factory();
        //try (var executorService = Executors.newFixedThreadPool(8)) {
        //try (var executorService = Executors.newCachedThreadPool()) {
        try (var executorService = Executors.newThreadPerTaskExecutor(threadFactory)) {
            IntStream.range(0, 1_000).forEach(i -> {
                executorService.submit(() -> {
                    try {
                        System.out.println(STR."Thread Name: \{Thread.currentThread()}");
                        System.out.println();
                        Thread.sleep(Duration.ofMillis(50L));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }
    }

}
