package com.dmslob.oca;

public class Deer {

    Deer() {
        System.out.println("Deer");
    }

    public Deer(int age) {
        System.out.println("DeerAge");
    }

    private boolean hasHorns() {
        return false;
    }

    public Animal hasAnimal() {
        return new Animal();
    }

    public static void main(String[] args) {
        Deer deer = new Reindeer(5);
        System.out.println(deer.hasHorns());

        Animal animal = new Unicorn(5);
        System.out.println(((Unicorn) animal).hasHorns());
    }
}

class Reindeer extends Deer {

    Reindeer(int age) {
        System.out.println("Reindeer");
    }

    public boolean hasHorns() {
        return true;
    }

    public Unicorn hasAnimal() {
        return new Unicorn(23);
    }
}

class Animal {

    public Animal() {
        System.out.println("Animal");
    }

    public Animal(int age) {
        System.out.println("AnimalAge");
    }

    private boolean hasHorns() {
        return false;
    }
}

class Unicorn extends Animal {

    public Unicorn(int UnicornAge) {
        System.out.println("Unicorn");
    }

    public boolean hasHorns() {
        return true;
    }
}

interface I {
    default void invalid() {
    }

    static void valid() {
    }
}

interface I2 extends I {

    //static void invalid() {} //WILL NOT COMPILE    

    default void valid() {
    } // this is ok.
}

class H {
    void test() {
    }
}

class P extends H {
    //static void test() {}
}

interface Account {
    default String getId() {
        return "0000";
    }
}

interface PremiumAccount extends Account {
    //String getId(); OK as well
    default String getId() {
        return "1111";
    }
}

