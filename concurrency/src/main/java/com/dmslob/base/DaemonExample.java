package com.dmslob.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaemonExample implements Runnable {

    private static final Logger log = LogManager.getLogger(DaemonExample.class);

    public final static ThreadGroup GROUP = new ThreadGroup("Daemon group");

    private final String name;

    public DaemonExample(String threadName) {
        this.name = threadName;
        Thread thread = new Thread(GROUP,  this, threadName);
        thread.setDaemon(true);
        thread.start();
    }

    public void run() {
        doSomeWork(3000);
        log.info(name + " is working");
    }

    public static void main(String s[]) {
        new DaemonExample("Daemon");
        doSomeWork(5000);
    }

    private static void doSomeWork(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}