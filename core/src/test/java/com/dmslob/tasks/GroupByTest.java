package com.dmslob.tasks;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Implement a method that groups strings by their length:
 * Requirements:
 * — Handle null and empty lists -> return an empty Map
 * — Ignore null elements in the list
 * — The order of strings in groups is preserved
 * — Group empty strings with key 0
 */
public class GroupByTest {

    Map<Integer, List<String>> groupByLength(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return new HashMap<>();
        }
        return strings.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        String::length,
                        LinkedHashMap::new,
                        Collectors.toList()));
    }

    @Test
    public void should_group_by_strings_length() {
        // given
        var expected = Map.of(
                1, List.of("a", "e"),
                2, List.of("bb", "dd"),
                3, List.of("ccc", "fff"));
        var strings = List.of("a", "bb", "ccc", "dd", "e", "fff");
        // when
        var actual = groupByLength(strings);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_group_by_strings_length_with_order_preservation() {
        // given
        var expected = Map.of(
                3, List.of("cat", "dog", "ant", "bee", "cow")
        );
        var strings = List.of("cat", "dog", "ant", "bee", "cow");
        // when
        var actual = groupByLength(strings);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_group_strings_by_length_when_nulls() {
        // given
        var expected = Map.of(
                5, List.of("hello", "world"),
                2, List.of("hi"),
                4, List.of("java"));
        var strings = Arrays.asList("hello", null, "world", "hi", null, "java");
        // when
        var actual = groupByLength(strings);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_group_strings_by_length_when_empty_strings() {
        // given
        var expected = Map.of(
                0, List.of("", ""),
                4, List.of("test"),
                1, List.of("a"));
        var strings = List.of("", "test", "", "a");
        // when
        var actual = groupByLength(strings);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_return_empty_map_when_list_is_null() {
        // given
        var expected = Map.of();
        List<String> strings = null;
        // when
        var actual = groupByLength(strings);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_return_empty_map_when_list_is_empty() {
        // given
        var expected = Map.of();
        List<String> strings = new ArrayList<>();
        // when
        var actual = groupByLength(strings);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
