package com.dmslob.chain.canonical;

public abstract class AbstractRequestHandler implements RequestHandler {

    private final RequestHandler next;

    public AbstractRequestHandler(RequestHandler next) {
        this.next = next;
    }

    protected void passToTheNext(Request request) {
        if (next != null) {
            next.handleRequest(request);
        }
    }
}
