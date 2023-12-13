package com.dmslob.starvation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Worker {

    private static final Logger log = LogManager.getLogger(Worker.class);

    public synchronized void work() {

        String threadName = Thread.currentThread().getName();
        String fileName = threadName + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Thread " + threadName + " wrote this message");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        while (true) {
            log.info(threadName + " is working");
        }
    }
}
