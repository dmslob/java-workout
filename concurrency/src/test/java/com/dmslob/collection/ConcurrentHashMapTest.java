package com.dmslob.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;

public class ConcurrentHashMapTest {

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentHashMapTest.class);

    @Test
    public void computeTest() {
        ArrayList<String> strings;
        ConcurrentMap<String, Integer> fruitsToCount = new ConcurrentHashMap<>();
        fruitsToCount.put("apple", 1);
        fruitsToCount.put("mango", 1);

        fruitsToCount.compute("apple", (key, value) -> value == null ? 1 : value + 1);
        fruitsToCount.compute("kiwi", (key, value) -> value == null ? 1 : value + 1);

        LOGGER.info(fruitsToCount.toString());
    }

    @Test
    public void computeIfAbsentTest() {
        ConcurrentMap<String, LongAdder> fruitsToCount = new ConcurrentHashMap<>();

        fruitsToCount.computeIfAbsent("apple", key -> new LongAdder()).increment();
        fruitsToCount.computeIfAbsent("mango", key -> new LongAdder()).increment();
        fruitsToCount.computeIfAbsent("apple", key -> new LongAdder()).increment();
        fruitsToCount.computeIfAbsent("kiwi", key -> new LongAdder()).increment();

        LOGGER.info(fruitsToCount.toString());
    }

    @Test
    public void computeIfPresentTest() {
        ConcurrentMap<String, LongAdder> fruitsToCount = new ConcurrentHashMap<>();
        fruitsToCount.computeIfPresent("banana", (key, value) -> {
            value.increment();
            return value;
        });

        Assert.assertNull(fruitsToCount.get("banana"));
    }
}
