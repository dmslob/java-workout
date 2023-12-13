package com.dmslob.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Resource implements AutoCloseable {

    public void use() {
        log.info("use()");
        throw new RuntimeException("Some exception");
    }

    @Override
    public void close() throws Exception {
        log.info("close()");
        throw new NullPointerException("This exception in the close method");
    }
}
