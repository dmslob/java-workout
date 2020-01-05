package com.dmslob.threads;

public class RunnableSelfManaged implements Runnable {

    private int count = 5;
    private Thread thread = new Thread(this);

    public RunnableSelfManaged() {
        thread.start();
    }

    public String toString() {
        return "#" + Thread.currentThread().getName()
                + "-> count:" + count + "    ";
    }

    public void run() {
        while (true) {
            System.out.println(this);
            if (--count == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new RunnableSelfManaged();
        }
    }
}
