package com.dmslob.generic.constructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class GenericConstructorTest {
    private static final Logger LOGGER = LogManager.getLogger(GenericConstructorTest.class);

    @Test
    public void constructorTest() {
        Product product = new Product("milk", 2.5);
        product.setSales(30);

        // we can also have a constructor with a generic type that's different from the class' generic type.
        RankEntry entry2 = new RankEntry(product);
        LOGGER.info(entry2.getData() + ", " + entry2.getRank());
    }

    @Test
    public void notRankableTest() {
        String product = "Milk";
        //Compile error - String is not Rankable
        //RankEntry entry = new RankEntry(product);
    }
}
