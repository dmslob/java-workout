package com.dmslob.java17;

import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StreamToListTest {

    @Test
    public void should_test_with_old_style() {
        // given
        Stream<String> stringStream = Stream.of("a", "b", "c");
        // when
        var stringList = stringStream.collect(Collectors.toList());
        // then
        assertThat(stringList).isNotNull();
    }

    @Test
    public void should_test_with_new_style() {
        // given
        var stringStream = Stream.of("a", "b", "c");
        // when
        var stringList = stringStream.toList();
        // then
        assertThat(stringList).isNotNull();
    }
}
