package com.dmslob.stream;

import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CollectorsTest {

    @Test
    public void should_use_two_collectors_when_teeing() {
        // given
        List<Integer> numbers = List.of(5, 10, 15, 20, 25);
        record SumCount(int sum, long count) {
        }
        // when
        SumCount sumCount = numbers.stream().collect(
                Collectors.teeing(
                        Collectors.summingInt(i -> i),  // First Collector: calculates sum
                        Collectors.counting(),                  // Second Collector: counts elements
                        SumCount::new                           // Merger: combines sum and count into a SumCount object
                ));
        // then
        assertThat(sumCount).isNotNull();
        assertThat(sumCount)
                .extracting(SumCount::sum, SumCount::count)
                .containsExactly(75, 5L);;
    }
}
