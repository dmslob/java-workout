package com.dmslob.oop.polimorphism;

public class Demo {
    public static void main(String[] args) {
        Primate thePrimate = new Primate();

        Lemur lemur = new Lemur();
        System.out.println(lemur.age);

        HasTail hasTail = lemur;
        System.out.println(hasTail.isTailStriped());

        Primate primate = lemur;
        System.out.println(primate.hasHair());

        Object newLemur = lemur;
        //System.out.println(newLemur.isTailStriped()); // Compile error

        if (thePrimate instanceof Lemur) {
            System.out.println("thePrimate is instance of Lemur");
        }

        if (thePrimate instanceof HasTail) {
            System.out.println("thePrimate is instance of HasTail");
        }

        if (lemur instanceof Primate) {
            System.out.println("lemur is instance of Primate");
        }

        if (primate instanceof Lemur) {
            System.out.println("primate is instance of Lemur");
        }

        if (lemur instanceof HasTail) {
            System.out.println("lemur is instance of HasTail");
        }

        if (primate instanceof HasTail) {
            System.out.println("primate is instance of HasTail");
        }

        if (primate instanceof Object) {
            System.out.println("primate is instance of Object");
        }
    }
}

class Primate {
    public boolean hasHair() {
        return true;
    }
}

interface HasTail {
    boolean isTailStriped();
}

class Lemur extends Primate implements HasTail {
    public int age = 10;

    public boolean isTailStriped() {
        return false;
    }
}
