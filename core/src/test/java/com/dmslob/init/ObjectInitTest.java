package com.dmslob.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class ObjectInitTest {

    private static final Logger LOGGER = LogManager.getLogger(ObjectInitTest.class);

    @Test
    public void should_not_create_an_object_when_exception() {
        Invoice invoice = null;

        try {
            invoice = new Invoice();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        Assert.assertNull(invoice);
    }
}
