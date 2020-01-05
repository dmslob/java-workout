package com.dmslob.base;

public class DaemonExample implements Runnable {

    // Отдельная группа, в которой будут находится все потоки ThreadTest
    public final static ThreadGroup GROUP = new ThreadGroup("Daemon demo");
    // Стартовое значение, указывается при создании объекта
    private int start;

    public DaemonExample(int s) {
        start = (s % 2 == 0) ? s : s + 1;
        new Thread(GROUP, this, "Thread " + start).start();
    }

    public void run() {
        // Начинаем обратный отсчет
        for (int i = start; i > 0; i--) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            // По достижению середины порождаем новый поток с половинным начальным значением
            if (start > 2 && i == start / 2) {
                new DaemonExample(i);
            }
        }
    }

    public static void main(String s[]) {
        new DaemonExample(16);
        new DaemonDemo();
    }
}

class DaemonDemo extends Thread {

    public DaemonDemo() {
        super("Daemon demo thread");
        setDaemon(true);
        start();
    }

    public void run() {
        Thread threads[] = new Thread[10];
        while (true) {
            // Получаем набор всех потоков из тестовой группы
            int count = DaemonExample.GROUP.activeCount();
            if (threads.length < count) threads = new Thread[count + 10];
            count = DaemonExample.GROUP.enumerate(threads);
            // Распечатываем имя каждого потока
            for (int i = 0; i < count; i++) {
                System.out.print(threads[i].getName() + ", ");
            }
            System.out.println();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
        }
    }
}

