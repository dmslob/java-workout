package com.dmslob.threadlocals;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private static final ThreadLocal<String> threadId = ThreadLocal.withInitial(() ->
            UUID.randomUUID().toString()
    );

    private void log(String message) {
        System.out.printf("[%s %s] %s\n",
                Thread.currentThread().getName(), threadId.get(), message);
    }

    @Test
    public void should_get_value_for_each_thread() throws InterruptedException {
        // given
        Runnable task = () -> {
            log("Starting operation...");
            log("Operation in progress...");
            log("Operation completed.");
        };
        Thread thread1 = new Thread(task, "Worker-1");
        Thread thread2 = new Thread(task, "Worker-2");
        // when
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        // then
        // check output
    }

    private void print(ThreadLocal<Integer> value) {
        System.out.println(Thread.currentThread().getName() + "=" + value.get());
    }

    @Test
    public void should_test_thread_locals_with_executor() {
        // given
        InheritableThreadLocal<Integer> value = new InheritableThreadLocal<>();
        value.set(1);
        // when
        print(value); // main=1
        executor.execute(() -> print(value)); // pool-thread-1=1

        value.set(2);
        print(value); // main=2
        executor.execute(() -> print(value)); // pool-thread-1=1 !?!
        // then
        // check output
    }
}
