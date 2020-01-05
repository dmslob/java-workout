package com.dmslob.exception;

public class UncaughtExceptionsDemo {

    void uncaughtExceptionHandlerPerThread() {
        Thread newThread = new Thread(new ThreadThrowingException());
        newThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread threadParam, Throwable e) {
                System.out.println(threadParam.getName() + " throws exception: " + e);
            }
        });
        newThread.start();
    }

    void uncaughtExceptionHandlerGlobally() {
        Thread.setDefaultUncaughtExceptionHandler(new LastChanceHandler());
        Thread newThread = new Thread(new ThreadThrowingException());
        newThread.start();
    }

    void uncaughtExceptionHandlerPerThreadGroup() {
        Thread newThread = new Thread(new ThreadThrowingException());
        newThread.setUncaughtExceptionHandler(newThread.getThreadGroup());
        newThread.start();
    }

    public static void main(String[] args) {
        UncaughtExceptionsDemo exceptionsDemo = new UncaughtExceptionsDemo();
        exceptionsDemo.uncaughtExceptionHandlerGlobally();
        exceptionsDemo.uncaughtExceptionHandlerPerThread();
        exceptionsDemo.uncaughtExceptionHandlerPerThreadGroup();
    }
}