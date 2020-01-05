package com.dmslob.pubsub;

import com.dmslob.pubsub.pub.Event;
import com.dmslob.pubsub.sub.Subscriber;

public class App {

    public static final String CREATE_CHANNEL = "action_create";
    public static final String UPDATE_CHANNEL = "action_update";

    public static void main(String[] args) {
        Subscriber subscriber1 = new Subscriber(1);
        Subscriber subscriber2 = new Subscriber(2);
        Subscriber subscriber3 = new Subscriber(3);
        Subscriber subscriber4 = new Subscriber(4);

        Event.operation.subscribe(CREATE_CHANNEL, subscriber1);
        Event.operation.subscribe(CREATE_CHANNEL, subscriber2);
        Event.operation.subscribe(UPDATE_CHANNEL, subscriber3);
        Event.operation.subscribe(UPDATE_CHANNEL, subscriber4);

        Message message1 = new Message("Create Action");
        Message message2 = new Message("Update Action");

        Event.operation.publish(CREATE_CHANNEL, message1);
        Event.operation.publish(UPDATE_CHANNEL, message2);
    }
}
