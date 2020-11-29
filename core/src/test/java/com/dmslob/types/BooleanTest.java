package com.dmslob.types;

import org.junit.Assert;
import org.junit.Test;

public class BooleanTest {
    @Test
    public void shouldParseNull() {
        String stringFlag = null;
        boolean flag = Boolean.parseBoolean(stringFlag);

        Assert.assertFalse(flag);
    }
}
