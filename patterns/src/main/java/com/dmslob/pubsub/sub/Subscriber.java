package com.dmslob.pubsub.sub;

import com.dmslob.pubsub.Message;
import com.dmslob.pubsub.OnMessage;

public class Subscriber {

    private int id;

    public Subscriber(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @OnMessage
    public void onMessage(Message message) {
        System.out.println("message: " + message.getMessage());
    }
}
