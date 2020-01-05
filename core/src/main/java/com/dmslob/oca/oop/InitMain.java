package com.dmslob.oca.oop;

import java.io.IOException;

public class InitMain {

//    {
//        try {
//            throw new Exception();
//        } catch (ExceptionInInitializerError e) {
//
//        }
//    } // compile error

    public static void main(String[] args) {
        //System.out.println(ICS.x);

        IUI subIUI = new SubIUI();
        subIUI.test();

        try {
            System.out.println("work real hard");
        } catch (StackOverflowError e) {

        } catch (IllegalArgumentException e) { // allowed, it is a more specific type than RuntimeException
        } catch (RuntimeException e) {
        }
    }
}

class Hopper {
    public void hop() throws Exception {
    }
}

class Bunny extends Hopper {
    public void hop() {
    }
}

interface IUI {
    default void test() {
        System.out.println("def");
    }
}

class SubIUI implements IUI {

    public void test() {
        System.out.println("sub");
    }
}

abstract class AB {

    abstract void test();
}

class Sub extends AB {

    void test() {
    }

    private interface IG {

    }
}

class IC {

    static {
        System.out.println("IC static block");
    }

    {
        System.out.println("init block");
    }

    public IC(int f) {
        System.out.println("IC Constructor");
    }

    private static abstract class GH {

    }
}

class ICS extends IC {

    static {
        System.out.println("ICS static block");
    }

    static int x = 89;

    public ICS() {
        super(5);
    }

    public ICS(int d) {
        super(d);
    }
}