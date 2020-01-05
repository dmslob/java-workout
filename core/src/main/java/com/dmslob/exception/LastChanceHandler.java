package com.dmslob.exception;

public class LastChanceHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread threadParam, Throwable e) {
        System.out.println(threadParam.getName() + " throws exception: " + e);
    }
}
