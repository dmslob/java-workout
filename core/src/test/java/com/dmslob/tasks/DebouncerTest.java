package com.dmslob.tasks;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DebouncerTest {
    private static final long DELAY_MS = 200;
    private Debouncer debouncer;

    @BeforeMethod
    public void setUp() {
        debouncer = new Debouncer(2, DELAY_MS);
    }

    @AfterMethod
    public void tearDown() {
        debouncer.shutdown();
    }

    @Test
    public void should_execute_only_once_when_calls_are_rapid() throws InterruptedException {
        // given
        AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(1);
        int expectedNumberOfCalls = 1;
        // when
        // Fire 5 rapid calls
        for (int i = 0; i < 5; i++) {
            debouncer.call(() -> {
                counter.incrementAndGet();
                latch.countDown();
            });
            Thread.sleep(50); // Less than the 200ms delay
        }
        // Wait for the final debounced call to finish
        boolean completed = latch.await(1, TimeUnit.SECONDS);
        // then
        assertThat(completed).isTrue();
        assertThat(counter.get()).isEqualTo(expectedNumberOfCalls);
    }

    @Test
    public void should_execute_multiple_times_if_delay_has_passed() throws InterruptedException {
        // given
        AtomicInteger counter = new AtomicInteger(0);
        int expectedNumberOfCalls = 2;
        var waitMs = DELAY_MS + 100;
        // when
        // First call
        debouncer.call(counter::incrementAndGet);
        // Wait longer than the delay
        Thread.sleep(waitMs);
        // Second call
        debouncer.call(counter::incrementAndGet);
        Thread.sleep(waitMs);
        // then
        assertThat(counter.get()).isEqualTo(expectedNumberOfCalls);
    }

    @Test
    public void should_be_thread_safe_when_accessed_by_multiple_threads() throws InterruptedException {
        // given
        AtomicInteger counter = new AtomicInteger(0);
        int threadCount = 10;
        int expectedNumberOfCalls = 1;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(1);
        // when
        Runnable task = () -> {
            try {
                startLatch.await(); // Ensure all threads hit the debouncer at once
                debouncer.call(() -> {
                    counter.incrementAndGet();
                    endLatch.countDown();
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
        for (int i = 0; i < threadCount; i++) {
            new Thread(task).start();
        }
        startLatch.countDown(); // Release the hounds!
        boolean finished = endLatch.await(1, TimeUnit.SECONDS);
        // then
        assertThat(finished).isTrue();
        assertThat(counter.get()).isEqualTo(expectedNumberOfCalls);
    }
}