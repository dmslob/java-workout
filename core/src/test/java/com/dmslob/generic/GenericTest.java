package com.dmslob.generic;

import com.dmslob.generic.constructor.GenericEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class GenericTest {
    private static final Logger LOGGER = LogManager.getLogger(GenericTest.class);

    @Test
    public void integerEntryTest() {
        GenericEntry<Integer> integerEntry = new GenericEntry<>(new Integer(1000), 1);
        LOGGER.info(integerEntry.getData());
        LOGGER.info(integerEntry.getRank());
    }

    @Test
    public void stringEntryTest() {
        GenericEntry<String> stringEntry = new GenericEntry<>("Entry", 1);

        LOGGER.info(stringEntry.getData());
        LOGGER.info(stringEntry.getRank());
    }
}
