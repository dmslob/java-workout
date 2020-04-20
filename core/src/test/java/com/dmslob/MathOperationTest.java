package com.dmslob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class MathOperationTest {

    private static final Logger LOGGER = LogManager.getLogger(MathOperationTest.class);

    @Test
    public void log2Test() {
        int n = 1024;
        int actualResult = MathOperation.log2(n);
        LOGGER.info("Log " + n + " to the base 2 = " + actualResult);

        Assert.assertTrue(actualResult == 10);
    }
}
