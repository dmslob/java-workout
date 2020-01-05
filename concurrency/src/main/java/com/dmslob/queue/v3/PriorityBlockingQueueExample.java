package com.dmslob.queue.v3;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

class Task extends Thread implements Comparable<Task> {

    private Random rand = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;

    public Task(int _priority) {
        priority = _priority;
    }

    @Override
    public int compareTo(Task arg) {
        return priority < arg.priority ? 1
                : (priority > arg.priority ? -1 : 0);
        //return Integer.compare(arg.priority, priority);
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
            System.out.println("Task " + id + ": priority " + priority);
        } catch (InterruptedException e) {
            System.out.println("Task interrupted");
        }
    }
}

class PrioritizedTaskProducer extends Thread {

    private Random rand = new Random(47);
    private Queue<Runnable> queue;

    public PrioritizedTaskProducer(Queue<Runnable> q) {
        queue = q;
    }

    @Override
    public void run() {
        // неблокируемая очередь
        for (int i = 0; i < 10; i++) {
            queue.add(new Task(rand.nextInt(10)));
            Thread.yield();
        }
        System.out.println("Finished PrioritizedTaskProducer");
    }
}

class PrioritizedTaskConsumer extends Thread {

    private PriorityBlockingQueue<Runnable> queue;

    public PrioritizedTaskConsumer(
            PriorityBlockingQueue<Runnable> q) {
        queue = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted");
        }
        System.out.println("Finished PrioritizedTaskConsumer");
    }
}

public class PriorityBlockingQueueExample {

    public static void main(String[] args) throws Exception {

        Random rand = new Random(47);
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();

        // ожидаем новую задачу
        new PrioritizedTaskConsumer(queue).start();
        // поставляем задачи
        new PrioritizedTaskProducer(queue).start();

    }
}

