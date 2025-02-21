package com.dmslob.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class SetTest {

    @Test
    public void should_make_sub_set_from_set() {
        // given
        var strings = new HashSet<String>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        var expectedSubSet = Set.of("b", "c");
        // when
        var subSet = strings.stream()
                .skip(1)
                .limit(2)
                .collect(Collectors.toSet());
        // then
        assertEquals(subSet, expectedSubSet);
    }

    @Test
    public void should_merge_two_sets() {
        // given
        Set<String> commonPoolNames = new HashSet<>();
        commonPoolNames.add("Anna");
        commonPoolNames.add("Teddy");
        Set<String> newNames = new HashSet<>();
        newNames.add("Anna");
        newNames.add("Billy");
        var expectedSet = Set.of("Anna", "Billy", "Teddy");
        // when
        boolean modified = commonPoolNames.addAll(newNames);
        // then
        assertTrue(modified);
        assertEquals(commonPoolNames, expectedSet);
    }
}
