package com.dmslob.functional;

import org.testng.annotations.Test;

import java.util.function.IntFunction;

/**
 * Continuations represent the remaining computation
 * to be performed after a particular function finishes executing.
 * In CPS, functions take continuations as arguments
 * and invoke them to pass control to the next stage of computation.
 */
public class ContinuationTest {

    void factorial(int n, IntFunction<Integer> cont) {
        if (n == 0) {
            cont.apply(1);
        } else {
            factorial(n - 1, result -> cont.apply(result * n));
        }
    }

    @Test
    public void should_test_continuation() {
        // given
        int n = 5;
        // when
        factorial(n, result -> {
            System.out.println("Factorial of %s: ".formatted(n) + result);
            return result;
        });
    }
}
