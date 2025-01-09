package com.dmslob.types;

public class HashCodeValuesTest {

    static long sink;

    public static void main(String... args) {
        for (int t = 0; t < 100000; t++) {
            for (int c = 0; c < 1000; c++) {
                sink = new Object().hashCode();
            }
            int hash = new Object().hashCode();
            if (hash == sink) {
                System.out.println(hash);
                System.out.println(sink);
            }
        }
    }
}
