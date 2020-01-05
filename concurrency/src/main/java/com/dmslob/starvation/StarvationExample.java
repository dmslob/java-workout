package com.dmslob.starvation;

public class StarvationExample {

    public static void main(String[] args) {

        Worker worker = new Worker();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> worker.work()).start();
        }
    }
}
