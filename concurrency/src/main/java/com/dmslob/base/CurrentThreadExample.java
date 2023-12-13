package com.dmslob.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurrentThreadExample {

    private static final Logger logger = LogManager.getLogger(CurrentThreadExample.class);

    public static void main(String[] args) {

        Thread currentThread = Thread.currentThread();
        currentThread.setName("My Thread");
        logger.info(" " + "current thread: " + currentThread.toString());

        try {
            for (int n = 5; n > 0; n--) {
                logger.info(" " + n);
                logger.info(" " + "Waiting 2 seconds...");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            logger.info("interrupted");
        }
    }
}
