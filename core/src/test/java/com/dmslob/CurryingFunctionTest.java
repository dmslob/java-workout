package com.dmslob;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

/**
 * Function Currying is a concept of breaking a function with many arguments
 * into many functions with single argument in such a way, that the output is same.
 * In other words, it is a technique of simplifying a multivalued argument function
 * into single-valued argument multi-functions.
 */
@Slf4j
public class CurryingFunctionTest {

    @Test
    public void addTwoNumbersTest() {
        // Given
        Integer a = 2, b = 3;
        Integer expected = 5;
        Function<Integer, Function<Integer, Integer>> curryAdder = u -> v -> u + v;
        // When
        Integer actual = curryAdder
                .apply(a)
                .apply(b);
        // Then
        log.info("{} + {} = {}", a, b, actual);
        Assert.assertEquals(expected, actual);
    }
}
