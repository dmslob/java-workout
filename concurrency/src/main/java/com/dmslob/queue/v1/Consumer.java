package com.dmslob.queue.v1;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Dmytro_Slobodenyuk on 9/4/2018.
 */
public class Consumer implements Runnable {

    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
