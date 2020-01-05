package com.dmslob.serialization.objectserialization;

import java.io.Serializable;
import java.util.Random;

public class Worm implements Serializable {

    private static Random rand = new Random(47);

    // It can be static class as well
    public class Data implements Serializable{

        private int n;

        public Data(int n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return Integer.toString(n);
        }
    }

    private Data[] data = {
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10))
    };

    private Worm next;
    private char c;

    public Worm() {
        System.out.println("Default constructor");
    }

    // Value of i == number of segments
    public Worm(int i, char x) {
        System.out.println("Worm constructor: " + i);
        c = x;
        if (--i > 0) {
            next = new Worm(i, (char) (x + 1));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");

        for (Data dt : data) {
            result.append(dt);
        }
        result.append(")");
        if (next != null) {
            result.append(next);
        }
        return result.toString();
    }
}


