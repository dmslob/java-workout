package com.dmslob.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class DequeTest {

    private static final Logger LOGGER = LogManager.getLogger(DequeTest.class);

    private Deque<String> fillUp(Deque<String> deque) {
        deque.add("element 1");         //add element at tail
        deque.addFirst("element 2"); //add element at head
        deque.addLast("element 3");  //add element at tail

        return deque;
    }

    @Test
    public void getElementTest() {
        Deque<String> deque = fillUp(new LinkedList<>());

        // Retrieving elements, without removing elements
        String head = deque.peek();
        LOGGER.info(head);

        String firstElement0 = deque.element();
        LOGGER.info(firstElement0);

        String firstElement1 = deque.getFirst();
        LOGGER.info(firstElement1);

        String lastElement = deque.getLast();
        LOGGER.info(lastElement);

        Assert.assertEquals(3, deque.size());
    }

    @Test
    public void removeElementTest() {
        Deque<String> deque = fillUp(new LinkedList<>());

        String firstElement0 = deque.remove();
        LOGGER.info(firstElement0);

        String firstElement1 = deque.removeFirst();
        LOGGER.info(firstElement1);

        String lastElement = deque.removeLast();
        LOGGER.info(lastElement);

        Assert.assertTrue(deque.size() == 0);
    }
}
