package com.dmslob.deadlock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dmytro_Slobodenyuk on 9/18/2018.
 */
public class DeadlockPoolExample {

    private static final Logger LOGGER = LogManager.getLogger(DeadlockPoolExample.class);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        pool.submit(() -> {
            try {
                LOGGER.info("First");
                pool.submit(() -> LOGGER.info("Second")).get();
                LOGGER.info("Third");
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("Error", e);
            }
        });

    }
}
