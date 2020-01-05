package com.dmslob.diningphilosophers.chandymisra;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class ChandyMisraMain {

    private ChandyMisraMain() {
    }

    public static void main(String[] args) throws InterruptedException {
        int numPhilosophers = 50;
        int numEats = 50;

        CountDownLatch startLatch = new CountDownLatch(numPhilosophers);

        List<ChandyMisraChopstick> chopsticks = new ArrayList<ChandyMisraChopstick>();
        for (int i = 0; i < numPhilosophers; i++) {
            chopsticks.add(new ChandyMisraChopstick());
        }

        List<ChandyMisraPhilosopher> philosophers = new ArrayList<ChandyMisraPhilosopher>();
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers
                    .add(new ChandyMisraPhilosopher(startLatch, chopsticks.get(i), numEats));

        }

        for (int i = 0; i < numPhilosophers; i++) {
            int rightIndex = i + 1;
            if (rightIndex == numPhilosophers) {
                rightIndex = 0;
            }

            int leftIndex = i - 1;
            if (leftIndex == -1) {
                leftIndex = numPhilosophers - 1;
            }

            philosophers.get(i).setLeftNeighbor(philosophers.get(leftIndex));
            philosophers.get(i).setRightNeighbor(philosophers.get(rightIndex));
        }

        ExecutorService ex = Executors.newCachedThreadPool();
        CompletionService<Void> cs = new ExecutorCompletionService<Void>(ex);

        for (Runnable philosopher : philosophers) {
            cs.submit(philosopher, null);
        }

        for (int i = 0; i < numPhilosophers; i++) {
            final Future<Void> future = cs.take();
            try {
                future.get();
            } catch (ExecutionException e) {
                System.out.println("Philosopher failed: " + e.getCause());
                ex.shutdownNow();
                break;
            }
        }

        ex.shutdown();
    }

}
