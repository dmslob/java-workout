package com.dmslob.base;

class NewThread implements Runnable {

    @Override
    public void run() {
        double calc;
        for (int i = 0; i < 50000; i++) {
            calc = Math.sin(i * i);
            if (i % 10000 == 0) {
                System.out.println(getName() + " counts " + i / 10000);
            }
        }
    }

    public String getName() {
        return Thread.currentThread().getName();
    }
}

public class PriorityExample {

    public static void main(String[] args) {

        // Подготовка потоков
        Thread t[] = new Thread[3];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new NewThread(), "Thread " + i);
            t[i].setPriority(Thread.MIN_PRIORITY
                    + (Thread.MAX_PRIORITY - Thread.MIN_PRIORITY) / t.length * i);
        }

        // Запуск потоков
        for (int i = 0; i < t.length; i++) {
            t[i].start();
            System.out.println(t[i].getName() + " started");
        }
    }

}

