package com.dmslob.reflection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShutDownHook {

    public void shutDown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> log.info("Shutdown Hook is running !")));
        log.info("Application Terminating ...");
    }
}
