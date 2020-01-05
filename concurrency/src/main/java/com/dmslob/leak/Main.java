package com.dmslob.leak;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dmytro_Slobodenyuk on 7/2/2018.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        LinkedBlockingQueue theQueue = new LinkedBlockingQueue();
        OrderRecord orderRecord = new OrderRecord(theQueue);
        OrderFeed orderFeed = new OrderFeed(theQueue);
        OrderQueueMonitor orderQueueMonitor = new OrderQueueMonitor(theQueue);
    }
}