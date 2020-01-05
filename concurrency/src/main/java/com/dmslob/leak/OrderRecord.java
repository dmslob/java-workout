package com.dmslob.leak;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dmytro_Slobodenyuk on 7/2/2018.
 */
public class OrderRecord implements Runnable {
    private final BlockingQueue<Order> orderQueue;

    public OrderRecord(BlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void start() {
        Thread thread = new Thread(this, "Order Recorder");
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Order order = orderQueue.take();
                recordOrder(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Record the order in the database
     * <p>
     * This is a dummy method
     *
     * @param order The order
     * @throws InterruptedException
     */
    public void recordOrder(Order order) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
}
