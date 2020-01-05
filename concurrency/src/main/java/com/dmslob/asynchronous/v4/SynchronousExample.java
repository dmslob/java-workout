package com.dmslob.asynchronous.v4;

public class SynchronousExample {

    private ISomeEventListener someEventListener;

    public void setSomeEventListener(ISomeEventListener someEventListener) {
        this.someEventListener = someEventListener;
    }

    public void doSomeHeavyCalculation(final long howLongInMs) {
        System.out.println("Doing some heavy work...");
        // some heavy work...
        try {
            Thread.sleep(howLongInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.someEventListener != null) {
            someEventListener.someEvent();
        }
    }

    public static void main(String[] args) {
        final SynchronousExample example = new SynchronousExample();

//        ISomeEventListener listener = new ISomeEventListener() {
//            @Override
//            public void someEvent() {
//                System.out.println("Received some event!");
//            }
//        };
//        example.setSomeEventListener(listener);
        // or
        example.setSomeEventListener(() -> System.out.println("Received some event!"));
        example.doSomeHeavyCalculation(1000);
    }
}

interface ISomeEventListener {
    void someEvent();
}