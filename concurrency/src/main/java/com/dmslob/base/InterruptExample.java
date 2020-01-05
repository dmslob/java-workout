package com.dmslob.base;

class TestRunnable implements Runnable {

    //прерывание потока
    @Override
    public void run() {
        // некоторое долгое действие
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
            System.out.println(sum);
            if (Thread.currentThread().isInterrupted()) {//проверяем выставлен ли флажок
                System.out.println("Interrupted");
                break;
            }
        }
        System.out.println(sum);
    }
}

public class InterruptExample {

    public static void main(String[] args) throws InterruptedException {

        Runnable r = new TestRunnable();
        Thread t = new Thread(r);
        t.start();
        t.sleep(10);
        t.interrupt();
    }
}

