package com.dmslob.base;

public class SynchronizeExample {

    public static void noSynchronizedAccess() {
        Callme target = new Callme();
        new Caller(target, "Hello.");
        new Caller(target, "Synchronized");
        new Caller(target, "World");
    }

    public static void synchronizedAccess() {
        Callme target = new Callme();
        new SynchCaller(target, "Hello.");
        new SynchCaller(target, "Synchronized");
        new SynchCaller(target, "World");
    }

    public static void main(String[] args) {
        noSynchronizedAccess();
        //synchronizedAccess();
    }
}

class Callme {

    void call(String msg) {
        System.out.println("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println("]");
    }

    synchronized void callSynch(String msg) {
        System.out.println("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    private String msg;
    private Callme target;

    public Caller(Callme t, String s) {
        target = t;
        msg = s;
        new Thread(this).start();
    }

    public void run() {
        target.call(msg);
    }
}

class SynchCaller implements Runnable {
    private String msg;
    private Callme target;

    public SynchCaller(Callme t, String s) {
        target = t;
        msg = s;
        new Thread(this).start();
    }

    public void run() {
        target.callSynch(msg);
    }
}
