package com.dmslob.oca.abstractclasses;

public class AbstractClassesMain {

    public static void main(String[] args) {

        //new Ferrary().testDrive();

        try {
            n();
        } catch (Error e) {
            System.out.println("error");
        }

        int a = 0;
        int b = 0;

        try {
          int f = a / b;
        } catch (ArithmeticException e) {

        } catch (RuntimeException e) {

        }
    }
    static void n() throws Error {
        throw new Error();
    }
}

interface CannotFly {
    void walk();
}

interface HasTail {
    public abstract Object getTailLength();
}

abstract class Falcon implements CannotFly, HasTail {

}

interface Walk {

    public default int getSpeed() {
        return 5;
    }
}

interface Run {

    public default int getSpeed() {
        return 10;
    }
}

class Cat implements Walk, Run { // DOES NOT COMPILE

    public int getSpeed() {
        return 180;
    }
}

interface HasFins {

    public default int getNumberOfFins() {
        return 4;
    }

    public default double getLongestFinLength() {
        return 20.0;
    }

    public default boolean doFinsHaveScales() {
        return true;
    }
}

interface SharkFamily extends HasFins {

    public default int getNumberOfFins() {
        return 8;
    }

    public double getLongestFinLength();

//    public boolean doFinsHaveScales() { // DOES NOT COMPILE
//        return false;
//    }
}


interface Car {

    default void move() {
        System.out.println("Car");
    }
}

interface SportCar extends Car {

}

class Ferrary implements SportCar {

    public void move() {
        System.out.println("SportCar");
    }

    public void testDrive() {
        move();
    }
}

abstract class Animal {

    protected int age;

    public void eat() {
        System.out.println("Animal is eating");
    }

    // final abstract String getName(); // DOES NOT COMPILE

    // private abstract void sing(); // DOES NOT COMPILE

    abstract String getName();

    protected abstract void sing();

//    public abstract void swim() {} // DOES NOT COMPILE
//
//    public abstract int getAge() { // DOES NOT COMPILE
//        return 10;
//    }
}

class Swan extends Animal {

    //public abstract void peck(); // DOES NOT COMPILE

    public String getName() {
        return "Swan";
    }

    public void sing() {

    }

    //private void sing() { } // DOES NOT COMPILE
}

//final abstract class Tortoise { // DOES NOT COMPILE
//}

abstract class Eagle extends Animal {
}

abstract class BigCat extends Animal {
    public abstract void roar();
}

class Lion extends BigCat {

    public String getName() {
        return "Lion";
    }

    public void sing() {
    }

    public void roar() {
        System.out.println("The Lion lets out a loud ROAR!");
    }
}

interface Engine {
}

interface CanFly {

    void fly(int speed);

    abstract void takeoff();

    public abstract double dive();
}

interface HasWhiskers {
    int getNumberOfWhiskers();
}

abstract class HarborSeal implements HasWhiskers {
}

class Ted extends HarborSeal { // Has to implement method

    public int getNumberOfWhiskers() {
        return 1;
    }
}

class LeopardSeal implements HasWhiskers { // Has to implement method

    public int getNumberOfWhiskers() {
        return 1;
    }
}

interface Herbivore {
    public int eatPlants();
}

interface Omnivore {
    public void eatPlants();
}

//interface Supervore extends Herbivore, Omnivore {} // DOES NOT COMPILE
// Different return types and first method clashes second
//abstract class AbstractBear implements Herbivore, Omnivore {} // DOES NOT COMPILE