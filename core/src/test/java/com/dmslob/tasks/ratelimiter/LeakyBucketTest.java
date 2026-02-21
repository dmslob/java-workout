package com.dmslob.tasks.ratelimiter;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LeakyBucketTest {

    private LeakyBucket leakyBucket;

    @AfterMethod
    public void tearDown() {
        if (leakyBucket != null) {
            leakyBucket.shutdown();
        }
    }

    @Test
    public void camelToSnakeAdvanced() {
        var t = "shouldExposeCurrentCount"
                .replaceAll("([a-z0-9])([A-Z])", "$1_$2")
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .toLowerCase();
        System.out.println(t);
    }

    @Test
    public void should_accept_requests_up_to_capacity() {
        // Given: A bucket with capacity 3 and a slow leak rate
        leakyBucket = new LeakyBucket(3, 1);
        // When/Then: We add 3 requests, all should be accepted
        assertThat(leakyBucket.addRequest(1)).isTrue();
        assertThat(leakyBucket.addRequest(2)).isTrue();
        assertThat(leakyBucket.addRequest(3)).isTrue();
    }

    @Test
    public void should_reject_requests_when_bucket_is_full() {
        // Given: A bucket with capacity 2 and a very slow leak rate (1 req/sec)
        leakyBucket = new LeakyBucket(2, 1);
        // Fill the bucket
        leakyBucket.addRequest(1);
        leakyBucket.addRequest(2);
        // When/Then: The 3rd request should be rejected immediately
        boolean isAccepted = leakyBucket.addRequest(3);
        assertThat(isAccepted)
                .as("Request should be dropped because the bucket is full")
                .isFalse();
    }

    @Test
    public void should_accept_new_requests_after_leaking() throws InterruptedException {
        // Given: Capacity 1, Leak Rate 10 requests/sec (1 leak every 100ms)
        leakyBucket = new LeakyBucket(1, 10);
        // Fill the bucket to its capacity
        assertThat(leakyBucket.addRequest(1)).isTrue();
        // Verify it is currently full
        assertThat(leakyBucket.addRequest(2)).isTrue();
        // When: We wait long enough for the bucket to leak (100ms required, waiting 150ms to be safe)
        Thread.sleep(150);
        // Then: The bucket should have leaked the first request and have space for a new one
        assertThat(leakyBucket.addRequest(3))
                .as("Request should be accepted after the bucket leaks")
                .isTrue();
    }
}