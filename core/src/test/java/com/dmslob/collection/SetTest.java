package com.dmslob.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    private static final Logger LOGGER = LogManager.getLogger(SetTest.class);

    @Test
    public void addAllTest() {
        Set<String> commonPoolNames = new HashSet<>();
        commonPoolNames.add("Anna");
        commonPoolNames.add("Teddy");

        Set<String> newNames = new HashSet<>();
        newNames.add("Anna");
        newNames.add("Billy");

        boolean modified = commonPoolNames.addAll(newNames);
        LOGGER.info(commonPoolNames);

        Assert.assertTrue(modified);
    }
}
