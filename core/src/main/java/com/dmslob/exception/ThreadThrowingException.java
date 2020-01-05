package com.dmslob.exception;

public class ThreadThrowingException implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("Exception from new Thread");
    }
}
