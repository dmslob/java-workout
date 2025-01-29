package com.dmslob.collections;

import java.util.PriorityQueue;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PriorityQueueTest {

    @Test
    public void getElementsTest() {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        pQueue.add(10);
        pQueue.add(20);
        pQueue.add(15);

        log.info(pQueue.toString());

        // top element
        log.info("{}", pQueue.peek());

        // Printing the top element and removing it
        log.info("{}", pQueue.poll());

        // top element again
        log.info("{}", pQueue.peek());
    }
}
