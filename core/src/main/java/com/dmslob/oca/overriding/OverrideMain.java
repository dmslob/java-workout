package com.dmslob.oca.overriding;

public class OverrideMain {

    public static void main(String[] args) {
        System.out.println(new Canine().getAverageWeight());
        System.out.println(new Wolf().getAverageWeight());
    }
}

class Canine {

    double getAverageWeight() {
        return 50;
    }

    public void fly() {
        System.out.println("Bird is flying");
    }

    Object getObject() {
        return new Object();
    }

    protected String getNumberOfHumps() {
        return "Undefined";
    }
}

class Wolf extends Canine {

    public double getAverageWeight() {
        return super.getAverageWeight() + 20;
    }

    public int fly(int height) {
        System.out.println("Bird is flying at " + height + " meters");
        return height;
    }

    public String getObject() {
        return "object";
    }

    protected boolean hasLegs() throws Exception {
        throw new Exception();
        // return false; // unreachable statement
    }

//    private int getNumberOfHumps() { // DOES NOT COMPILE
//        return 2;
//    }

//    public double getAverageWeight() {
//        return getAverageWeight()+20; // INFINITE LOOP
//    }
}

class InsufficientDataException extends Exception {
}

class Reptile {

    protected double getHeight() throws InsufficientDataException {
        return 2;
    }

    protected int getLength() {
        return 10;
    }
}

class Snake extends Reptile {

//    protected double getHeight() throws Exception { // DOES NOT COMPILE
//        return 2;
//    }

//    protected int getLength() throws InsufficientDataException { // DOES NOT COMPILE
//        return 10;
//    }
}