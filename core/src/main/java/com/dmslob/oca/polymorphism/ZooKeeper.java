package com.dmslob.oca.polymorphism;

public class ZooKeeper {

    public static void main(String[] args) {
        Animal animal = new Gorilla();
        System.out.println(animal.getName());

        TheRodent theRodent = new TheRodent();
        theRodent.test("test");
        theRodent.test(67);
        theRodent.test(new Integer(34));
        theRodent.test(new Exception("error"));

        Nocturnal nocturnal = (Nocturnal) new Owl();
        System.out.println(nocturnal.isBlind());

        Spider spider = new Spider();
        spider.printName(4);
        spider.printName(9.0);
    }
}

class Snake {
}

class Cobra extends Snake {
}

class GardenSnake {
}

class SnakeHandler {
    private Snake snake;

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void test() {
        new SnakeHandler().setSnake(new Snake());
        new SnakeHandler().setSnake(new Cobra());
        new SnakeHandler().setSnake(null);
    }
}

interface Aquatic {

    public default int getNumberOfGills(int i) {
        return 2;
    }
}

class ClownFish implements Aquatic {

    public String getNumberOfGills() {
        return "4";
    }

//    public String getNumberOfGills(int input) {
//        return "6";
//    } // compile error
}

class Arthropod {

    public void printName(double input) {
        System.out.println("Arthropod");
    }
}

class Spider extends Arthropod {

    public void printName(int input) {
        System.out.println("Spider");
    }
}

interface Nocturnal {

    default boolean isBlind() {
        return true;
    }
}

class Owl implements Nocturnal {

    public boolean isBlind() {
        return false;
    }
}

interface CanFly {
    void fly();
}

interface HasWings {
    public abstract Object getWindSpan();
}

abstract class Falcon implements CanFly, HasWings {

    public static final int speed = 50;

    static void eat() {

    }

    public final void tet() {

    }
}

abstract class Car {

    public final void drive() {
        System.out.println("drive");
    }

}

class Audi extends Car {

    public void test() {
        Car car = new Audi();

    }

//    public void drive() {
//
//    }
}

class Animal {

    public String getName() {
        return "Animal";
    }
}

class Gorilla extends Animal {

//    protected String getName() { // DOES NOT COMPILE
//        return "Gorilla";
//    }

    public String getName() { // DOES NOT COMPILE
        return "Gorilla";
    }
}

interface CanHop {
}

class Frog implements CanHop {

    public void main() {
        Frog frog = new TurtleFrog();
        TurtleFrog turtleFrog = new TurtleFrog();
        CanHop canHop = new TurtleFrog();
        Object o = new TurtleFrog();
    }
}

class BrazilianHornedFrog extends Frog {
}

class TurtleFrog extends Frog {
}

class TheRodent {

    public void test(Object o) {
        System.out.println("o: " + o.toString());
    }

    protected static Integer chew() throws Exception {
        System.out.println("Rodent is chewing");
        return 1;
    }
}

class Beaver extends TheRodent {

//    public Number chew() throws RuntimeException {
//        System.out.println("Beaver is chewing on wood");
//        return 2;
//    } // DOES NOT COMPILE, bad return type
}

interface HasExoskeleton {
    abstract int getNumberOfSections();
}

abstract class Insect implements HasExoskeleton {
    abstract int getNumberOfLegs();
}

class Beetle extends Insect {

    int getNumberOfLegs() {
        return 6;
    }

    public int getNumberOfSections() {
        return 1;
    }
}