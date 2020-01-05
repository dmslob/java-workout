package com.dmslob.base;

import java.util.concurrent.TimeUnit;

public class WaitNotifyDemo {

    public static void main(String[] args) {

        Common c = new Common();
        new On(c).start();
        new Off(c).start();
    }
}

class Common {

    boolean on = false;

    synchronized void on() {
        on = true;
        notifyAll();
    }

    synchronized void off() {
        on = false;
        notifyAll();
    }

    synchronized void waitOn() {
        while (!on) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("interrupted");
            }
        }
    }

    synchronized void waitOff() {
        while (on) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("interrupted");
            }
        }
    }
}

class On extends Thread {

    private Common common;

    On(Common c) {
        common = c;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            common.waitOff();
            System.out.println("On");
            common.on();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }
    }
}

// one value is waiting another, to be changed.
class Off extends Thread {

    private Common common;

    Off(Common c) {
        common = c;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            common.waitOn();
            System.out.println("Off");
            common.off();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }
    }
}