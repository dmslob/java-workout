package com.dmslob.oca.oop;

public class Mouse {

    private int numTeeth;
    private int numWhiskers;
    private int weight;

    private final int speed;

    public Mouse(int weight) {
        this(weight, 16); // calls constructor with 2 parameters
    }

    public Mouse(int weight, int numTeeth) {
        this(weight, numTeeth, 6); // calls constructor with 3 parameters
    }

    public Mouse(int weight, int numTeeth, int numWhiskers) {
        this.weight = weight;
        this.numTeeth = numTeeth;
        this.numWhiskers = numWhiskers;
        this.speed = 3;
    }

    public void print() {
        System.out.println(weight + " " + numTeeth + " " + numWhiskers);
    }

    public static void main(String[] args) {
        Mouse mouse = new Mouse(15);
        mouse.print();
    }
}

class Parent {

    protected static final String NONE = "";

    Parent(int a) {
    }

    void test() {

    }
}

class Child extends Parent {

    Child(int a) {
        super(a);
    }

//    int test() {
//        return 1;
//    } // compile error
}

abstract class GH {
    static {
        System.out.println();
    }

    {
        System.out.println();
    }
}

class JK extends GH {

}
