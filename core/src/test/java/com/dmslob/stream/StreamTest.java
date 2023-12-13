package com.dmslob.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
public class StreamTest {

    @Test
    public void should_have_warning_when_no_comparator_contract_in_method_reference() {
        /*
         * Warning:
         * Method reference mapped to Comparator interface does not fulfill
         * the Comparator contract
         * use .max(Comparator.naturalOrder())
         */
        Integer maxNumber = Stream.of(-1, 0, 1)
                .max(Math::max).get();

        Assert.assertEquals(Integer.valueOf(-1), maxNumber);
    }

    @Test
    public void should_dropWhile() {
        List<Integer> c1 = List.of(2, 4, 6, 3, 1, 8, 7);
        var r = c1.stream()
                .dropWhile(n -> n % 2 == 0)
                .toList();
        System.out.println(r);
    }

    @Test
    public void should_takeWhile() {
        List<Integer> numbers = List.of(25, 26, 28, 31, 29, 27, 22);
        List<Integer> result = numbers.stream()
                .takeWhile(t -> t <= 30)
                .toList();
        System.out.println(result);
    }

    @Test
    public void should_sort_stream_with_comparator() {
        // given
        var points = new ArrayList<Point>();
        points.add(new Point(10, 20));
        points.add(new Point(5, 10));
        points.add(new Point(1, 100));
        points.add(new Point(50, 2000));

        Comparator<Integer> pointComparator = Integer::compareTo;

        // when
        var sortedPoints = points.stream()
                //.sorted((p1, p2) -> p1.getX().compareTo(p2.getX()))
                .sorted(Comparator.comparing(Point::getX, pointComparator))
                .collect(Collectors.toList());
        // then
        assertThat(sortedPoints).isNotSameAs(points);
        assertThat(sortedPoints.get(0).getX())
                .isEqualTo(1);
    }

    @Test
    public void foo() {
        // given
        var integers = List.of(1, 2, 3, 4, 5);
        List<Integer> storage = Collections.synchronizedList(new ArrayList<>());

        // when
        var o = integers.parallelStream()
                // Don't do this! It uses a stateful lambda expression.
                .map(e -> {
                    storage.add(e);
                    return e;
                })
                .toList();

        System.out.println(storage);
    }

    @Test
    public void should_throw_exception_when_interference_occurs() {
        var strings = new ArrayList<>(Arrays.asList("one", "two"));
        // This will fail as the peek operation will attempt to add the
        // string "three" to the source after the terminal operation has
        // commenced.
        assertThatThrownBy(() -> strings
                .stream()
                // Don't do this! Interference occurs here.
                .peek(s -> strings.add("three"))
                .collect(Collectors.joining(" ")))
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void should_copy_source() {
        // given
        var users = new ArrayList<>(Arrays.asList("Bob", "Arnold"));
        var usersStream = users.stream();
        users.add("Anna");

        // when
        String joinedUserNames = usersStream
                .collect(Collectors.joining(" "));
        // then
        assertThat(joinedUserNames).isEqualTo("Bob Arnold Anna");
    }
}