package com.dmslob.java21;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class VirtualThreadsTest {
    // - The thread-per-request style
    //
    // - The stacks of virtual threads are stored in Java's garbage-collected heap as stack chunk objects.
    // - Virtual threads support thread-local variables (ThreadLocal)
    // and inheritable thread-local variables (InheritableThreadLocal),
    // just like platform threads, so they can run existing code that uses thread locals.

    @Test
    public void should_test_runnable() throws InterruptedException {
        // given
        System.out.println("Start Virtual thread");
        Runnable task = () -> {
            System.out.println(STR."Hello from thread! Thread ID: \{
                    Thread.currentThread().threadId()}");
            System.out.println(STR."Thread isDaemon: \{Thread.currentThread().isDaemon()}");
            System.out.println(STR."Thread Name: \{Thread.currentThread().getName()}");
            System.out.println(STR."Thread State: \{Thread.currentThread().getState()}");
            System.out.println(STR."Thread isVirtual: \{Thread.currentThread().isVirtual()}");
            System.out.println(STR."Thread classloader: \{Thread.currentThread().getContextClassLoader().getName()}");
        };
        Thread virtualThread = Thread.ofVirtual().start(task);
        virtualThread.join(); // Wait for the virtual thread to complete

        System.out.println("Start Platform thread");
        Thread platformThread = Thread.ofPlatform().start(task);
        platformThread.join(); // Wait for the virtual thread to complete
        System.out.println("Test thread finished.");
        // when
        // then
    }

    @Test
    public void should_test_pool() throws InterruptedException {
        // given
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10).forEach(i -> {
                executor.submit(() -> {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }
        // when
        // then
    }
}
