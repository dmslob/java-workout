package com.dmslob.threads;

public class ThreadDemo extends Thread {

    private int count = 5;
    private static int threadCount = 0;

    public ThreadDemo() {
        super(Integer.toString(++threadCount));
        start();
    }

    public String toString() {
        return "#" + getName() + "-> count:" + count + "    ";
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
            new ThreadDemo();
        }
    }
}
