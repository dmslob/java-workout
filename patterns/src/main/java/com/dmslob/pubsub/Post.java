package com.dmslob.pubsub;

public abstract class Post {

    private String message;

    public Post(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
