package com.dmslob.exception;

public class Resource implements AutoCloseable {

    public void use() {
        throw new RuntimeException("This exception in the use method");
    }

    @Override
    public void close() throws Exception {
        throw new NullPointerException("This exception in the close method");
    }
}
