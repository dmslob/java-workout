package com.dmslob.condition;

final class HandoffTaker implements Runnable {

    private final Handoff<Object> handoff;

    public HandoffTaker(Handoff<Object> handoff) {
        this.handoff = handoff;
    }

    @Override
    public void run() {
        System.out.println("Getting object...");
        Object o;
        try {
            o = this.handoff.take();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            return;
        }
        System.out.println("Got an object " + o);
    }
}
