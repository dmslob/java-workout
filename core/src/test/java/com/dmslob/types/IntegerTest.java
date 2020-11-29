package com.dmslob.types;

import org.junit.Assert;
import org.junit.Test;

public class IntegerTest {

    @Test
    public void shouldHaveNegativeResult() {
        int x = Integer.MAX_VALUE + 10;

        Assert.assertTrue(x < 0);
    }

    @Test
    public void shouldAssignHex() {
        int x = 0xff;
        Assert.assertEquals(255, x);

        /** Since Java represents a byte using 8 bits and because a byte is a signed data type,
         *  the value of 0xff is -1
         *  */
        byte y = (byte) 0xff;
        Assert.assertEquals(-1, y);
    }

    @Test
    public void testBitwiseAnd() {
        int rgba = 272214023;

        int r = rgba >> 24 & 0xff;
        Assert.assertEquals(16, r);

        int g = rgba >> 16 & 0xff;
        Assert.assertEquals(57, g);

        int b = rgba >> 8 & 0xff;
        Assert.assertEquals(168, b);

        int a = rgba & 0xff;
        Assert.assertEquals(7, a);
    }
}