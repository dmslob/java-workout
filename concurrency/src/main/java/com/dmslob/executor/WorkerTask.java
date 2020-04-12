package com.dmslob.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkerTask implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(WorkerTask.class);

    private String name;

    public WorkerTask(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Executing : " + name);
    }
}
