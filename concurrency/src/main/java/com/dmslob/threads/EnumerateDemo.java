package com.dmslob.threads;

public class EnumerateDemo {

    public static void main(String[] args) {

        new MyThread().start();

        Thread[] threads = new Thread[Thread.activeCount()];// currently active threads
        int n = Thread.enumerate(threads);
        // until second thread alive
        for (int i = 0; i < n; i++) {
            if (threads[i].isAlive()) {
                System.out.println(threads[i].toString() + " " + threads[i].getState());
            }
        }
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        while (true) ;
    }
}
