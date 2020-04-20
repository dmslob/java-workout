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
        Integer expected = 5;
        Function<Integer, Function<Integer, Integer>> curryAdder = u -> v -> u + v;
        Integer actual = curryAdder.apply(2).apply(3);
        LOGGER.info("Add 2, 3 : " + actual);

        Assert.assertEquals(expected, actual);
    }
}
