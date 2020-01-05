package com.dmslob.queue.v1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Dmytro_Slobodenyuk on 9/4/2018.
 */
public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}
