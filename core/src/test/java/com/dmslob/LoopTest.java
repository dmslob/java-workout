package com.dmslob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class LoopTest {

    private static final Logger LOGGER = LogManager.getLogger(LoopTest.class);

    @Test
    public void whileEmptyTest() {
        LOGGER.info("Before while");
        while (true) ;
        //LOGGER.info("Unreachable statement"); // Compile error
    }

    @Test
    public void doWhileEmptyTest() {
        LOGGER.info("Before while");
//        do
//            while (true) ; // Compile error
    }
}
