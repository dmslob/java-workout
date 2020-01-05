package com.dmslob.observer.canonical;

public class LoggingEventListener implements EventListener {
    public void onEvent(Event event) {
        System.out.println("EVENT: " + event.getName());
    }
}
