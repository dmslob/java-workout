package com.dmslob.exception;

public class Resource implements AutoCloseable {

    public void use() {
        System.out.println("Use");
        //throw new RuntimeException("This exception in the use method");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Close()");
        //throw new NullPointerException("This exception in the close method");
    }
}
