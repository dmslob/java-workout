package com.dmslob.threads;

import com.dmslob.base.DaemonExample;

import java.util.concurrent.TimeUnit;

public class DaemonDemo implements Runnable {

    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new DaemonDemo());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("all daemons started ...");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
