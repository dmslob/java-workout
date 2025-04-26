package com.dmslob.cache;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LRUCacheTest {

    @Test
    public void should_get_last_recently_used_item() {
        // given
        LRUCache<String, Integer> lruCache = new LRUCache<>(4);
        lruCache.set("Bob", 23);
        lruCache.set("Jack", 24);
        lruCache.set("Anna", 34);
        // when
        lruCache.get("Bob");
        // then
        assertThat(lruCache.getHead())
                .isEqualTo(23);
    }
}
