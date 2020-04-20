package com.dmslob.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.PriorityQueue;

public class PriorityQueueTest {

    private static final Logger LOGGER = LogManager.getLogger(PriorityQueueTest.class);

    @Test
    public void getElementsTest() {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        pQueue.add(10);
        pQueue.add(20);
        pQueue.add(15);

        LOGGER.info(pQueue.toString());

        // top element
        LOGGER.info(pQueue.peek());

        // Printing the top element and removing it
        LOGGER.info(pQueue.poll());

        // top element again
        LOGGER.info(pQueue.peek());
    }
}
