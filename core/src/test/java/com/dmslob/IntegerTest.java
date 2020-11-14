package com.dmslob;

import org.junit.Assert;
import org.junit.Test;

public class IntegerTest {

    @Test
    public void should_have_negative_result() {
        int x = Integer.MAX_VALUE + 10;

        Assert.assertTrue(x < 0);
    }
}