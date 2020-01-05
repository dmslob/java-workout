package com.dmslob.asynchronous.v4;

public class AsynchronousExample {

    private ISomeEventListener someEventListener;

    public void setSomeEventListener(ISomeEventListener someEventListener) {
        this.someEventListener = someEventListener;
    }

    public void doSomeHeavyCalculation(final long howLongInMs) {
        // some heavy work...
        new Thread(() -> {
            try {
                System.out.println("Doing some heavy work...");
                Thread.sleep(howLongInMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.someEventListener != null) {
                someEventListener.someEvent();
            }
        }).start();
    }

    public static void main(String[] args) {
        final AsynchronousExample example = new AsynchronousExample();

        example.setSomeEventListener(() -> System.out.println("Received some event!"));
        example.doSomeHeavyCalculation(1000);
    }
}
