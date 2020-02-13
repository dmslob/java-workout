package com.dmslob.synchronizer.phaser;

import java.util.concurrent.Phaser;

public class OrderPhaseDemo {
    public static void main(String[] args) {

        Phaser phaser = new Phaser(1);
        int currentPhase;

        System.out.println("Starting");
        new Thread(new Order("A", phaser)).start();
        new Thread(new Order("B", phaser)).start();
        new Thread(new Order("C", phaser)).start();

        // Wait for all threads to complete phase one
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " complete");

        // Wait for all threads to complete phase two
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " complete");

        // Wait for all threads to complete phase three
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " complete");

        phaser.arriveAndDeregister();

        if (phaser.isTerminated()) {
            System.out.println("The phaser is terminated");
        }
    }
}

class Order implements Runnable {
    private String name;
    private Phaser phaser;

    public Order(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
        this.phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " Beginning phase one");
        phaser.arriveAndAwaitAdvance(); // Signal arrival.

        // Paused a bit to prevent jumbled output. This is for illustration only.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread " + name + " Beginning phase two");
        phaser.arriveAndAwaitAdvance(); // Signal arrival.

        // Paused a bit to prevent jumbled output. This is for illustration only.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread " + name + " Beginning phase three");
        phaser.arriveAndDeregister();
    }
}