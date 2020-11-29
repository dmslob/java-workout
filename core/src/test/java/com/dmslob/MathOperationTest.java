package com.dmslob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class MathOperationTest {

    private static final Logger LOGGER = LogManager.getLogger(MathOperationTest.class);

    @Test
    public void testLog2() {
        int n = 1024;
        int actualResult = MathOperation.log2(n);
        LOGGER.info("Log {} to the base 2 = {}", n, actualResult);

        Assert.assertEquals(10, actualResult);
    }
}
