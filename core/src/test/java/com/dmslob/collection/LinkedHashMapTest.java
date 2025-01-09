package com.dmslob.collection;

import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class LinkedHashMapTest {
    @Test
    void givenLinkedHashMapObject_whenAddingNewEntry_thenEldestEntryIsRemoved() {
        // Given
        final int MAX_SIZE = 4;
        String[] expectedArrayAfterFive = { "Two", "Three", "Four", "Five" };
        String[] expectedArrayAfterSix = { "Three", "Four", "Five", "Six" };
        var linkedHashMap = new LinkedHashMap<Integer, String>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > MAX_SIZE;
            }
        };
        // When
        linkedHashMap.put(1, "One");
        linkedHashMap.put(2, "Two");
        linkedHashMap.put(3, "Three");
        linkedHashMap.put(4, "Four");
        linkedHashMap.put(5, "Five");
        // Then
        assertArrayEquals(expectedArrayAfterFive, linkedHashMap.values()
                .toArray());
        // When
        linkedHashMap.put(6, "Six");
        // Then
        assertArrayEquals(expectedArrayAfterSix, linkedHashMap.values()
                .toArray());
    }
}
