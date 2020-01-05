package com.dmslob.oca.exceptions;

import java.io.IOException;
import java.util.function.Consumer;

public class DoSomething {

    public static void main(String[] args) {
        //new DoSomething().go(); // AEC and stacktrace

        //new Dog().test();

        //new HasSoreThroatException().test();

        t2();
        //t3();
    }

    static void withNoCompileError() {
        try {
            System.out.println("try block");
        } catch (Exception e) {
            System.out.println("Never");
        } // without compile error

        try {
            System.out.println("try block");
        } catch (Throwable e) {
            System.out.println("Never");
        } // without compile error

//        try {
//            System.out.println("try block");
//        } catch (IOException e) {
//            System.out.println("Never");
//        } // compile error, never trown
    }

    static void ohNoAgain() throws Exception {
        throw new IOException();
    }

//    static void ohNoAgain() throws IOException {
//        throw new Exception();
//    } compile error

    static void t3() {
        System.out.println("a");
        try {
            System.out.println("b");
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("c");
            throw new RuntimeException("1");
        }
    }

    static void t2() {
        System.out.println("a");
        try {
            System.out.println("b");
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("c");
            throw new RuntimeException("1");
        } catch (RuntimeException e) {
            System.out.println("d");
            throw new RuntimeException("2");
        } finally {
            System.out.println("e");
            throw new RuntimeException("3");
        }
    }

    static void t1() {
        try {
            System.out.println("work real hard");
        } catch (StackOverflowError e) {
            System.out.println("");
        } catch (RuntimeException e) {
            System.out.println("");
        }
    }

    public void go() {
        System.out.print("A");
        try {
            stop();
        } catch (ArithmeticException e) {
            System.out.print("B");
        } finally {
            System.out.print("C");
        }
        System.out.print("D");
    }

    void stop() {
        System.out.print("E");
        Object x = null;
        x.toString();
        System.out.print("F");
    }

    void ohNo() throws IOException {
        //System.out.println("ok"); // OK
        //throw new Exception(); // compile error
        throw new IOException();
        //throw new RuntimeException(); // OK
        //throw new IllegalArgumentException(); // OK
    }
}

class HasSoreThroatException extends Exception {
    void n() throws Error {
        throw new Error("hey");
    }

    void test() {
        try {
            n();
        } catch (Error e) {
            System.out.println(e.getMessage());
        }

    }
}

class TiredException extends RuntimeException {
}

interface Roar {
    void roar() throws HasSoreThroatException;
}

class Lion implements Roar {

    //public void roar() {} // OK

    //public void roar() throws HasSoreThroatException {} // OK

    //public void roar() throws IllegalArgumentException {} // OK

    public void roar() throws TiredException {
    }
}

class Dog {

    String name;

    void parseName() {
        System.out.println("1");
        try {
            System.out.println("2");
            int x = Integer.parseInt(name);
            System.out.println("3");
        } catch (NumberFormatException e) {
            System.out.println("4");
        }
    }

    static Exception getError() {
        return new Exception();
    }

    void test() {
        Dog leroy = new Dog();
        leroy.name = "Leroy";
        leroy.parseName();
        System.out.println("5");
    }
}