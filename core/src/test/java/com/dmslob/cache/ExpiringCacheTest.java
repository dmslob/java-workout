package com.dmslob.cache;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ExpiringCacheTest {

    @Test
    public void should_test_cache() throws InterruptedException {
        // given
        ExpiringCache<String, String> cache = new ExpiringCache<>(1000);
        // when
        cache.put("key1", "value1", 5000); // expires after 5 seconds
        TimeUnit.MILLISECONDS.sleep(3000);

        System.out.println(cache.get("key1").orElse("Expired")); // Prints "value1"
        TimeUnit.MILLISECONDS.sleep(3000);

        System.out.println(cache.get("key").orElse("Expired")); // Prints "Expired"

        // then
        System.out.printf("Cache Hits: %s%n", cache.getHits());
        System.out.printf("Cache Misses: %s%n", cache.getMisses());

        cache.shutdown(); // Clear the cache
    }
}
