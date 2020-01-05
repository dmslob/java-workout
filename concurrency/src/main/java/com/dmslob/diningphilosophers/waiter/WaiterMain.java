package com.dmslob.diningphilosophers.waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class WaiterMain {

    public static void main(String[] args) throws InterruptedException {
        int numPhilosophers = 50;
        int numEats = 50;

        CountDownLatch startLatch = new CountDownLatch(numPhilosophers);

        List<WaiterChopstick> chopsticks = new ArrayList<WaiterChopstick>();
        for (int i = 0; i < numPhilosophers; i++) {
            chopsticks.add(new WaiterChopstick());
        }

        List<WaiterPhilosopher> philosophers = new ArrayList<WaiterPhilosopher>();
        // handle last philosopher by hand

        Waiter waiter = new Waiter();

        for (int i = 0; i < numPhilosophers; i++) {
            int rightChopstickIndex = i + 1;
            if (rightChopstickIndex == numPhilosophers) {
                rightChopstickIndex = 0;
            }
            philosophers.add(new WaiterPhilosopher(startLatch, chopsticks.get(i), chopsticks.get(rightChopstickIndex),
                    numEats,
                    waiter));

        }

        waiter.initialize(philosophers);

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
