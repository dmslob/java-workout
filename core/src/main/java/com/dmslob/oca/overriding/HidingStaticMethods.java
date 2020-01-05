package com.dmslob.oca.overriding;

public class HidingStaticMethods {

    public static void main(String[] args) {
        Bear.eat();
        Panda.eat();
        Panda.sneeze();

        Kangaroo joey = new Kangaroo();
        joey.getMarsupialDescription();
        joey.getKangarooDescription();
        System.out.println(new Marsupial().flag);
        System.out.println(new Kangaroo().flag);

        Jellyfish jellyfish = new Jellyfish();
        Animal animal = new Jellyfish();
        System.out.println(jellyfish.length);
        System.out.println(animal.length);
    }
}

class Animal {
    public int length = 2;

    public Number getSize() {
        return 12;
    }
}

class Jellyfish extends Animal {

    public int length = 5;

    public Integer getSize() {
        return 23;
    }
}

class Bear {

    public static void eat() {
        System.out.println("Bear is eating");
    }

    public static void sneeze() {
        System.out.println("Bear is sneezing");
    }

    public void hibernate() {
        System.out.println("Bear is hibernating");
    }
}

class Panda extends Bear {

    public static void eat() {
        System.out.println("Panda bear is chewing");
    }

//    public void sneeze() { // DOES NOT COMPILE
//        System.out.println("Panda bear sneezes quietly");
//    }
//
//    public static void hibernate() { // DOES NOT COMPILE
//        System.out.println("Panda bear is going to sleep");
//    }
}

class Marsupial {

    public int flag = 7;

    public static boolean isBiped() {
        return false;
    }

    public void getMarsupialDescription() {
        System.out.println("Marsupial walks on two legs: " + isBiped());
    }

    final void hop() {

    }
}

class Kangaroo extends Marsupial {

    public boolean flag = true;

    public static boolean isBiped() {
        return true;
    }

    public void getKangarooDescription() {
        System.out.println("Kangaroo hops on two legs: " + isBiped());
    }

    int hop(int g) {
        return 2;
    }
}