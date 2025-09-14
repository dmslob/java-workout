package com.dmslob.data.problems;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class SnippetTest {


    @Test
    public void should_sum_integers() {
        // given
        int expectedSum = 10;
        int accumulator = 0;
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        // when
        int result = sumReduce(integers, accumulator);
        // then
        assertThat(result).isEqualTo(expectedSum);
        // or when
        result = integers.stream().mapToInt(Integer::intValue).sum();
        // then
        assertThat(result).isEqualTo(expectedSum);
        // or when
        result = integers.stream().reduce(0, Integer::sum);
        // then
        assertThat(result).isEqualTo(expectedSum);
    }

    /*
     * A subroutine call in tail position can thus be optimised effectively by replacing
     * it with a jump instruction. For this reason, tail call optimisation is also called
     * tail call elimination. This is important because every time a recursive subroutine
     * calls itself without TCO, it requires more space for the stack.
     */
    private int sumReduce(List<Integer> integers, int accumulator) {
        if (integers.isEmpty()) {
            return accumulator;
        }
        int first = integers.get(0);
        List<Integer> rest = integers
                .stream()
                .skip(1)
                .collect(toList());
        return sumReduce(rest, accumulator + first);
    }

    @Test
    public void should_reverse_integer() {
        // given
        int integer = 1234;
        int expectedInteger = 4321;
        // when
        int reversedInteger = Snippets.reverseInt(integer);
        // then
        assertThat(reversedInteger).isEqualTo(expectedInteger);
    }

    @Test
    public void should_reverse_string() {
        // given
        String word = "home";
        String expectedWord = "emoh";
        // when
        String reversedWord = Snippets.reverseString(word);
        // then
        assertThat(reversedWord).isEqualTo(expectedWord);
    }

    /**
     * Given a 2D integer array numsisUni where nums[i] is a non-empty array of distinct positive integers,
     * return the list of integers that are present in each array of nums sorted in ascending order.
     */
    @Test
    public void should_find_intersection_for_2D_array() {
        // given
        var numbers = new int[][]{
                {3, 1, 2, 4, 5},
                {1, 2, 3, 4},
                {3, 4, 5, 6}
        };
        var expectedResult = new int[]{3, 4};

        // when
        var result = Snippets.findIntersection(numbers);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    /**
     * Given an array of integers temperatures represents the daily temperatures,
     * return an array answer such that answer[i] is the number of days you have
     * to wait after the ith day to get a warmer temperature.
     * If there is no future day for which this is possible, keep answer[i] == 0 instead.
     */
    @Test
    public void should_find_warmer_days() {
        int[] t = {13, 12, 15, 11, 9, 12, 16};
        var answer = temperatures(t);
        // Output: [2, 1, 4, 2, 1, 1, 0]
        System.out.println(Arrays.toString(answer));

        int[] t2 = {73, 74, 75, 71, 69, 72, 76, 73};
        var r = temperatures(t2);
        //Output: [1,1,4,2,1,1,0,0]
        System.out.println(Arrays.toString(r));
    }

    /*
     * https://leetcode.com/problems/daily-temperatures/
     */
    public int[] temperatures(int[] t) {
        record Pair(int value, int index) {
        }
        var stack = new Stack<Pair>();
        int[] answer = new int[t.length];

        for (int i = t.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().value <= t[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                answer[i] = stack.peek().index - i;
            }
            stack.push(new Pair(t[i], i));
        }
        return answer;
    }

    /*
     * https://practice.geeksforgeeks.org/problems/the-celebrity-problem/1
     */
    public Person findCelebrity(Person[] persons) {
        int l = 0, r = persons.length - 1;
        while (l != r) {
            if (persons[l].knows(persons[r])) {
                l++;
            } else {
                r--;
            }
        }
        for (int i = 0; i < persons.length; i++) {
            if (i != l && (!persons[i].knows(persons[l]) || persons[l].knows(persons[i]))) {
                return null;
            }
        }
        return persons[l];
    }

    record Person(Person p) {
        boolean knows(Person person) {
            return p() == person;
        }
    }
}
