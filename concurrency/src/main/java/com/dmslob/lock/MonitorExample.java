package com.dmslob.lock;

public class MonitorExample {

    public static void main(String[] args) {
        final SyncClass sync = new SyncClass();

        new Thread(() -> {
            for (; ; ) {
                sync.one();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                sync.two();
            }
        }).start();
    }
}
