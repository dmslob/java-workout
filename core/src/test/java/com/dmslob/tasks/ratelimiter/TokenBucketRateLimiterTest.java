package com.dmslob.tasks.ratelimiter;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TokenBucketRateLimiterTest {

    @Test
    public void should_consume_until_capacity_exceeded() {
        // when
        var limiter = new TokenBucketRateLimiter(5, 1);
        // then
        // Consume all available tokens
        for (int i = 0; i < 5; i++) {
            assertThat(limiter.tryConsume()).isTrue();
        }
        // The next one should fail
        assertThat(limiter.tryConsume()).isFalse();
    }

    @Test
    public void should_refill_tokens_over_time() throws InterruptedException {
        // when
        var limiter = new TokenBucketRateLimiter(2, 2);
        // then
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isFalse();
        // Wait for refill (~1 second gives 2 tokens)
        Thread.sleep(1100);
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isTrue();
    }

    @Test
    public void should_not_exceed_capacity_after_refill() throws InterruptedException {
        // when
        var limiter = new TokenBucketRateLimiter(3, 10);
        Thread.sleep(1000);
        // then
        assertThat(limiter.getAvailableTokens())
                .isLessThanOrEqualTo(3);
    }

    @Test
    public void should_be_thread_safe_under_concurrent_access() throws Exception {
        // given
        int capacity = 50;
        var limiter = new TokenBucketRateLimiter(capacity, 1);
        ExecutorService executor = Executors.newFixedThreadPool(20);
        List<Future<Boolean>> futures = new ArrayList<>();
        // when
        for (int i = 0; i < 100; i++) {
            futures.add(executor.submit(limiter::tryConsume));
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        long successful = futures.stream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Boolean::booleanValue)
                .count();
        // then
        assertThat(successful)
                .isLessThanOrEqualTo(capacity);
    }

    @Test
    public void should_throw_exception_for_invalid_arguments() {
        // when | then
        assertThatThrownBy(() ->
                new TokenBucketRateLimiter(0, 1))
                .isInstanceOf(IllegalArgumentException.class);
        // then
        assertThatThrownBy(() ->
                new TokenBucketRateLimiter(1, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}