package com.dmslob.constants;

public class FinalTest {

    static final int N_ITERATIONS = 1000000;

    static String testFinal() {
        final String a = "a";
        final String b = "b";
        return a + b;
    }

    static String testNonFinal() {
        String a = "a";
        String b = "b";
        return a + b;
    }

    public static void main(String[] args) {
        long tStart, tElapsed;

        tStart = System.currentTimeMillis();
        for (int i = 0; i < N_ITERATIONS; i++) {
            testFinal();
        }
        tElapsed = System.currentTimeMillis() - tStart;
        System.out.println("Method with finals took " + tElapsed + " ms");

        tStart = System.currentTimeMillis();
        for (int i = 0; i < N_ITERATIONS; i++) {
            testNonFinal();
        }
        tElapsed = System.currentTimeMillis() - tStart;
        System.out.println("Method without finals took " + tElapsed + " ms");
    }
}
