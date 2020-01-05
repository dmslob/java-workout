package com.dmslob.synchronizer.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {

        Exchanger<StringBuilder> ex = new Exchanger<>();
        new Consumer(ex).start();
        new Producer(ex).start();
    }
}

class Producer extends Thread {

    Exchanger<StringBuilder> ex;
    StringBuilder str = new StringBuilder();

    Producer(Exchanger<StringBuilder> e) {
        ex = e;
    }

    @Override
    public void run() {
        char ch = 'A';
        for (int i = 0; i < 3; i++) {
            // строка из 5 символов
            for (int j = 0; j < 5; j++) {
                str.append((char) ch++);
            }

            // передаем
            try {
                str = ex.exchange(str);
                System.out.println("Passed: " + str);
            } catch (InterruptedException exc) {
                System.out.println("Producer interrupted");
            }
        }
    }
}

class Consumer extends Thread {

    Exchanger<StringBuilder> ex;

    StringBuilder str;

    Consumer(Exchanger<StringBuilder> e) {
        ex = e;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                // получили данные
                str = ex.exchange(new StringBuilder());
                System.out.println("Got: " + str);
            } catch (InterruptedException exc) {
                System.out.println("Consumer interrupted");
            }
        }
    }
}