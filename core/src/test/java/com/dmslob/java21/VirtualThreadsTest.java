package com.dmslob.java21;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        Thread virtualThread = Thread.ofVirtual().start(task_1);
        virtualThread.join(); // Wait for the virtual thread to complete

        // Builder
        Thread.Builder builder = Thread.ofVirtual().name("my-worker");
        Runnable task_2 = () -> {
            System.out.println("Running task_2");
            System.out.println(STR."Thread Name: \{Thread.currentThread().getName()}");
            System.out.println();
        };
        Thread t = builder.start(task_2);
        t.join();

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
}
