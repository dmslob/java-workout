package com.dmslob.oca.polymorphism;

public class Lemur extends Primate implements HasTail {

    public int age = 10;

    public boolean isTailStriped() {
        return false;
    }

    public static void main(String[] args) {
        Lemur lemur = new Lemur();
        System.out.println(lemur.age);

        HasTail hasTail = lemur;
        System.out.println(hasTail.isTailStriped());
        System.out.println(((Lemur) hasTail).age);

        Primate primate = lemur;
        System.out.println(primate.hasHair());

        Primate thePrimate = lemur;
        //System.out.println(thePrimate.isTailStriped()); // DOES NOT COMPILE
        Lemur lemur1 = (Lemur) thePrimate;

        Primate primate1 = new Lemur();
        HasTail tail = new Lemur();
        System.out.println(tail.isTailStriped());
        tail = (Lemur) primate;
        System.out.println(((Lemur) tail).age);

        Rodent theRodent = new Rodent();
        if (theRodent instanceof Capybara) {
            System.out.println("Yes");
        }
        //Capybara capybara = (Capybara) theRodent; // Throws ClassCastException at runtime

        Rodent theCapybara = new Capybara();
        if (theCapybara instanceof Capybara) {
            System.out.println("Yes");
        }
        Capybara newCapybara = (Capybara) theCapybara;

        Bird theBird = new Peacock();
        theBird.displayInformation();
    }
}

class Primate {

    boolean hasHair() {
        return true;
    }
}

interface HasTail {
    boolean isTailStriped();
}

class Rodent {
}

class Capybara extends Rodent {
}

class Bird {

    public String getName() {
        return "Unknown";
    }

    public void displayInformation() {
        System.out.println("The bird name is: " + getName());
    }
}

class Peacock extends Bird {

    public String getName() {
        return "Peacock";
    }
}
