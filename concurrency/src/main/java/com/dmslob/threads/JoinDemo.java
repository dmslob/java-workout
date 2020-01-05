package com.dmslob.threads;

public class JoinDemo {

    public static void main(String[] args) {
        joinOrder();
    }

    static void joinOrder() {
        ParallelTask task1 = new ParallelTask();
        ParallelTask task2 = new ParallelTask();
        ParallelTask task3 = new ParallelTask();

        final Thread t1 = new Thread(task1, "Thread - 1");
        final Thread t2 = new Thread(task2, "Thread - 2");
        final Thread t3 = new Thread(task3, "Thread - 3");

        task2.setPredecessor(t1);
        task3.setPredecessor(t2);

        t1.start();
        t2.start();
        t3.start();
    }

    static void joinWithInterrupt() {
        Sleeper s1 = new Sleeper("Sleep1", 1500);
        Sleeper s2 = new Sleeper("Sleep2", 1500);

        Joiner j1 = new Joiner("Joiner1", s1);
        Joiner j2 = new Joiner("Joiner2", s2);

        j2.interrupt();
    }
}

class Sleeper extends Thread {

    private int duration;

    public Sleeper(String name, int time) {
        super(name);
        duration = time;
        start();
    }

    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " is interrupted");
        }
        System.out.println(getName() + " is active");
    }
}

class Joiner extends Thread {

    private Sleeper sleeper;

    public Joiner(String name, Sleeper _sleeper) {
        super(name);
        sleeper = _sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println(getName() + " is interrupted");
        }
        System.out.println(getName() + " is completed");
    }
}

class ParallelTask implements Runnable {

    private Thread predecessor;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Started");
        if (predecessor != null) {
            try {
                predecessor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Finished");
    }

    public void setPredecessor(Thread t) {
        this.predecessor = t;
    }
}
