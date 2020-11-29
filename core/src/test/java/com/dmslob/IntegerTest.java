package com.dmslob;

import org.junit.Assert;
import org.junit.Test;

public class IntegerTest {

    @Test
    public void shouldHaveNegativeResult() {
        int x = Integer.MAX_VALUE + 10;

        Assert.assertTrue(x < 0);
    }
}