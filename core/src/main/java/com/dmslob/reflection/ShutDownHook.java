package com.dmslob.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShutDownHook {
    private static final Logger LOGGER = LogManager.getLogger(ShutDownHook.class);

    public void shutDown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> LOGGER.info("Shutdown Hook is running !")));
        LOGGER.info("Application Terminating ...");
    }
}
