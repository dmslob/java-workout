package com.dmslob.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class DelayQueueDemo {

    public static void main(String[] args) {
        Random rand = new Random(47);
        DelayQueue<Task> queue = new DelayQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.put(new Task(rand.nextInt(5000)));
        }

        new DelayedTaskConsumer(queue).start();
    }
}

class Task extends Thread implements Delayed {

    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<Task> sequence = new ArrayList<>();

    public Task(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed arg) {
        Task that = (Task) arg;
        if (trigger < that.trigger) {
            return -1;
        }
        if (trigger > that.trigger) {
            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.println(" Task " + id + " " + delta);
    }
}

class DelayedTaskConsumer extends Thread {

    private DelayQueue<Task> queue;

    public DelayedTaskConsumer(DelayQueue<Task> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (queue.isEmpty()) {
                    break;
                }
                queue.take().start();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Finished");
    }
}