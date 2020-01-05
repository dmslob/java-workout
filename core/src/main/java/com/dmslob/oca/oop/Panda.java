package com.dmslob.oca.oop;

import java.util.function.Predicate;

public class Panda {

    int age;

    public static void main(String[] args) {
        Panda p1 = new Panda();
        p1.age = 1;
        check(p1, panda -> panda.age < 5);

        Climber.check((h, l) -> h < l, 5);

        Secret secret = new MySecret();
        check(secret, (e) -> {
            return "test".length() > 0;
        });
    }

    private static void check(Panda panda, Predicate<Panda> pred) {
        String result = pred.test(panda) ? "match" : "not match";
        System.out.println(result);
    }

    private static void check(Secret secret, Predicate<Secret> pred) {
        String result = pred.test(secret) ? "match" : "not match";
        System.out.println(result);
    }
}

class Climber {

    static void check(Climb climb, int height) {
        if (climb.isTooHigh(height, 10))
            System.out.println("too high");
        else
            System.out.println("ok");
    }
}

interface Climb {
    boolean isTooHigh(int heigh, int limit);
}

interface Secret {
    String magic(double d);
}

class MySecret implements Secret {
    public String magic(double d) {
        return "Poof";
    }
}