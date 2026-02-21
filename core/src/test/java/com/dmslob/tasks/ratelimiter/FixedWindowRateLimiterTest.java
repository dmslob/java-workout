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

public class FixedWindowRateLimiterTest {

    @Test
    public void should_allow_requests_up_to_limit_within_window() {
        // given
        var limiter = new FixedWindowRateLimiter(3, 1000);
        // when & then
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isTrue();
        // limit reached
        assertThat(limiter.tryConsume()).isFalse();
    }

    @Test
    public void should_reset_counter_after_window_expires() throws InterruptedException {
        // given
        var limiter = new FixedWindowRateLimiter(2, 300);
        // when & then
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isFalse();
        // wait for a window to reset
        Thread.sleep(350);
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isTrue();
        assertThat(limiter.tryConsume()).isFalse();
    }

    @Test
    public void should_not_exceed_max_requests_under_concurrency() throws Exception {
        // given
        int limit = 20;
        var limiter = new FixedWindowRateLimiter(limit, 1000);
        ExecutorService executor = Executors.newFixedThreadPool(10);
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
                .isLessThanOrEqualTo(limit);
    }

    @Test
    public void should_throw_exception_for_invalid_arguments() {
        // when | then
        assertThatThrownBy(() ->
                new FixedWindowRateLimiter(0, 1000))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() ->
                new FixedWindowRateLimiter(10, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_expose_current_count() {
        // given
        var limiter = new FixedWindowRateLimiter(5, 1000);
        // when
        limiter.tryConsume();
        limiter.tryConsume();
        // then
        assertThat(limiter.getCurrentCount())
                .isEqualTo(2);
    }
}