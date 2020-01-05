package com.dmslob.condition;

final class HandoffGiver implements Runnable {

    private final Handoff<Object> handoff;

    public HandoffGiver(Handoff<Object> handoff) {
        this.handoff = handoff;
    }

    @Override
    public void run() {
        Object o = new Object();
        System.out.println("Giving object " + o);
        try {
            this.handoff.put(o);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            return;
        }
        System.out.println("Give object " + o);
    }
}
