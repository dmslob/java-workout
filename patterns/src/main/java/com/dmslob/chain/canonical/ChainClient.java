package com.dmslob.chain.canonical;

public class ChainClient {

    public static void main(String[] args) {
        Request request = new Request(5);
        RequestHandler handler = new AuthRequestHandler(new LoggingRequestHandler(null));
        handler.handleRequest(request);
    }
}
