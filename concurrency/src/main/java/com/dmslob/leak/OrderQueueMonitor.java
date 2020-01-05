package com.dmslob.leak;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dmytro_Slobodenyuk on 7/2/2018.
 */
public class OrderQueueMonitor implements Runnable {
    private final BlockingQueue<Order> orderQueue;

    public OrderQueueMonitor(BlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void start() {

        Thread thread = new Thread(this, "Order Queue Monitor");
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(2);
                int size = orderQueue.size();
                System.out.println("Queue size is:" + size);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
