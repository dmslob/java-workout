package com.dmslob.data.problems;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LinkedListCycleTest {

    public static class Node {
        private final int data;
        private Node next;
        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Test
    public void should_return_false_when_no_cycle() {
        // given
        var five = new Node(5, null);
        var four = new Node(4, five);
        var three = new Node(3, four);
        var two = new Node(2, three);
        var first = new Node(1, two);
        // when
        var result = hasCycle(first);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void should_return_true_when_cycle() {
        // given
        var five = new Node(5, null);
        var four = new Node(4, five);
        var three = new Node(3, four);
        var two = new Node(2, three);
        var first = new Node(1, two);
        five.setNext(first);
        // when
        var result = hasCycle(first);
        // then
        assertThat(result).isTrue();
    }

    public boolean hasCycle(Node head) {
        if (head == null) return false;
        var slow = head;
        var fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
