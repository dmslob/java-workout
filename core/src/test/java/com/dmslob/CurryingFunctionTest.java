package com.dmslob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class CurryingFunctionTest {

    private static final Logger LOGGER = LogManager.getLogger(CurryingFunctionTest.class);

    @Test
    public void addTwoNumbersTest() {
        // Given
        int a = 2;
        int b = 3;
        Integer expected = 5;
        Function<Integer, Function<Integer, Integer>> curryAdder = u -> v -> u + v;

        // When
        Integer actual = curryAdder.apply(a).apply(b);

        // Then
        LOGGER.info("{} + {} = {}", a, b, actual);
        Assert.assertEquals(expected, actual);
    }
}
