package com.dmslob.tasks;

import org.testng.annotations.Test;

import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class RateLimitStoreTest {

    private final RateLimitStore store = new RateLimitStore(100);

    @Test
    public void should_allow_requests_within_limit() {
        // given
        String key = "testKey";
        var maxRequests = 3;
        var timeWindow = 5000;
        // when
        boolean first = store.tryAcquire(key, maxRequests, timeWindow);
        boolean second = store.tryAcquire(key, maxRequests, timeWindow);
        boolean third = store.tryAcquire(key, maxRequests, timeWindow);
        // then
        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isTrue();
    }

    @Test
    public void should_block_when_limit_exceeded() {
        // given
        String key = "limitExceededKey";
        var maxRequests = 2;
        var timeWindow = 5000;
        // when
        store.tryAcquire(key, maxRequests, timeWindow);
        store.tryAcquire(key, maxRequests, timeWindow);
        boolean thirdAttempt = store.tryAcquire(key, maxRequests, timeWindow);
        // then
        assertThat(thirdAttempt).isFalse();
    }

    @Test
    public void should_reset_after_time_window_expires() throws InterruptedException {
        // given
        String key = "windowResetKey";
        var maxRequests = 1;
        var timeWindow = 200;
        // when
        store.tryAcquire(key, maxRequests, timeWindow);
        boolean blocked = store.tryAcquire(key, maxRequests, timeWindow);
        // then
        assertThat(blocked).isFalse();
        // when
        Thread.sleep(250);
        boolean allowedAgain = store.tryAcquire(key, maxRequests, timeWindow);
        // then
        assertThat(allowedAgain).isTrue();
    }

    @Test
    public void should_be_thread_safe() throws InterruptedException {
        // given
        String key = "concurrentKey";
        int maxRequests = 10;
        int threadCount = 50;
        ConcurrentLinkedQueue<Boolean> results;
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            var latch = new CountDownLatch(threadCount);
            results = new ConcurrentLinkedQueue<>();
            IntStream.range(0, threadCount).forEach(i ->
                    executor.submit(() -> {
                        // when
                        boolean result = store.tryAcquire(key, maxRequests, 5000);
                        results.add(result);
                        latch.countDown();
                    })
            );
            latch.await();
            executor.shutdown();
        }
        long allowedCount = results.stream().filter(Boolean::booleanValue).count();
        // then
        assertThat(allowedCount).isEqualTo(maxRequests);
    }
}
