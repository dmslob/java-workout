package com.dmslob.observer;

import com.dmslob.observer.canonical.Event;
import com.dmslob.observer.canonical.EventListener;
import com.dmslob.observer.canonical.Observer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModernTimeServer implements Observer {

    private static final String TIME_FORMAT = "HH:mm:ss";

    private final List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    public void reportTime() {
        Event event = new Event(new SimpleDateFormat(TIME_FORMAT).format(new Date()));
        listeners.forEach(listener -> listener.onEvent(event));
    }
}
