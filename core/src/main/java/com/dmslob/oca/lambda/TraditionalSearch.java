package com.dmslob.oca.lambda;

import java.util.ArrayList;
import java.util.List;

public class TraditionalSearch {

    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));

        print(animals, new CheckIfHopper()); // pass class that does check
        // or
        print(animals, a -> a.canHop());
        print(animals, a -> a.canSwim());
        print(animals, a -> a.canSwim());

        System.out.println(howMany(true, true, true));
        System.out.println(howMany(true, new boolean[2]));

        List<String> list = new ArrayList<>();
        list.removeIf(s -> s.isEmpty());
        list.removeIf(s -> {
            return s.isEmpty();
        });
        list.removeIf((String s) -> s.isEmpty());

        caller((e) -> "Poof");
        caller((e) -> {
            return "Poof";
        });
        //caller((e) -> { String e = ""; return "Poof";}); // e is already defined

        MyEventConsumer consumer = (a, b) -> {
            System.out.println("");
        };
    }

    public static int howMany(boolean b, boolean... b2) {
        return b2.length;
    }

    public void moreG(String[] values, int... nums) {
    }

    final void get() {
        return;
    }

    final int get(float f) {
        short ff = 89;
        return ff;
    }

    final static void get(int a) {

    }

    static final void got() {

    }

    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            if (checker.test(animal)) // the general check
                System.out.print(animal + " ");
        }
        System.out.println();
    }

    static void caller(Secret secret) {
        System.out.println(secret.magic(34.5));
    }
}

interface Secret {
    String magic(double d);
}

class MySecret implements Secret {
    public String magic(double d) {
        return "Poof";
    }
}

interface MyEventConsumer {
    void consume(int a, int b);
}

interface MyConsumer {
    boolean consume(int a);
}