package com.dmslob.cache;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleTtlCacheTest {

    @Test
    public void should_test_cache_for_default_ttl() throws InterruptedException {
        // given
        long defaultTtl = TimeUnit.SECONDS.toMillis(5);
        SimpleTtlCache<String, String> cache = new SimpleTtlCache<>(defaultTtl);
        // when
        cache.put("session:123", "user-data");
        // then
        assertThat(cache.get("session:123")).isEqualTo("user-data");
        // when
        TimeUnit.SECONDS.sleep(2);
        // then
        assertThat(cache.get("session:123")).isEqualTo("user-data");
        // when
        TimeUnit.SECONDS.sleep(3);
        // then
        assertThat(cache.get("session:123")).isNull();
    }

    @Test
    public void should_test_cache_for_custom_ttl() throws InterruptedException {
        // given
        long defaultTtl = TimeUnit.SECONDS.toMillis(5);
        SimpleTtlCache<String, String> cache = new SimpleTtlCache<>(defaultTtl);
        // when
        cache.put("session:123", "user-data-123",
                TimeUnit.SECONDS.toMillis(3));
        cache.put("session:124", "user-data-124",
                TimeUnit.SECONDS.toMillis(5));
        // then
        assertThat(cache.get("session:123"))
                .isEqualTo("user-data-123");
        assertThat(cache.get("session:124"))
                .isEqualTo("user-data-124");
        // when
        TimeUnit.SECONDS.sleep(3);
        // then
        assertThat(cache.get("session:123")).isNull();
        // when
        TimeUnit.SECONDS.sleep(2);
        // then
        assertThat(cache.get("session:124")).isNull();
    }
}
