package com.dmslob.algorithms;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// two-pointer technique
public class LinkedListTwoPointerTest {

    public record Node(int data, Node next) {
    }

    @Test
    public void should_find_middle_element_of_list() {
        // given
        var five = new Node(5, null);
        var four = new Node(4, five);
        var three = new Node(3, four);
        var two = new Node(2, three);
        var first = new Node(1, two);
        var expected = 3;
        // when
        var result = findMiddle(first);
        // then
        assertThat(result).isEqualTo(expected);
    }

    /*
    * Problem: Given a singly LinkedList, find its middle element.
    * For example, if our input LinkedList is 1->2->3->4->5,
    * then the output should be 3.
     */
    public int findMiddle(Node head) {
        var slow = head;
        var fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }
}
