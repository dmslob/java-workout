package com.dmslob.base;

class Task extends Thread {

    boolean suspendFlag;

    Task(String threadname, ThreadGroup tgOb) {
        super(tgOb, threadname);
        System.out.println("New thread: " + this);
        suspendFlag = false;
        start();
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(getName() + ": " + i);
                Thread.sleep(1000);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in " + getName());
        }
        System.out.println(getName() + " exiting.");
    }

    void suspendTask() {
        suspendFlag = true;
    }

    synchronized void resumeTask() {
        suspendFlag = false;
        notify();
    }
}

public class ThreadGroupExample {

    public static void main(String[] args) {

        ThreadGroup groupA = new ThreadGroup("Group A");
        ThreadGroup groupB = new ThreadGroup("Group B");

        // добавляем задачи в группы
        Task ob1 = new Task("One", groupA);
        Task ob2 = new Task("Two", groupA);

        Task ob3 = new Task("Three", groupB);
        Task ob4 = new Task("Four", groupB);

        System.out.println("Here is output from list():");
        groupA.list();
        groupB.list();

        System.out.println();
        System.out.println("Suspending Group A");
        Thread tga[] = new Thread[groupA.activeCount()];
        groupA.enumerate(tga); // задачи в группе

        // приостанавливам потоки
        for (int i = 0; i < tga.length; i++) {
            ((Task) tga[i]).suspendTask();
        }

        // ждем 4 секунды
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println("Resuming Group A");
        // возобновляем работу
        for (int i = 0; i < tga.length; i++) {
            ((Task) tga[i]).resumeTask();
        }
        // ожидаем окончания работы потоков группы
        try {
            System.out.println("Waiting for threads to finish.");
            ob1.join();
            ob2.join();
            ob3.join();
            ob4.join();
        } catch (Exception e) {
            System.out.println("Exception in Main thread");
        }
        System.out.println("Main thread exiting.");
    }
}

