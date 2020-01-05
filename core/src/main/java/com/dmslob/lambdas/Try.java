package com.dmslob.lambdas;

public class Try<Exception, R> {

    private final Exception failure;
    private final R success;

    public Try(Exception failure, R success) {
        this.failure = failure;
        this.success = success;
    }
}
