package com.dmslob.generic.exceptions;

import java.util.List;

public class App {
    public static void main(String[] args) {

    }
}

// class MyException<T> extends Exception {} // may not to extend

class GenericClassWithInnerClass<T> {
//    private class MyInnerException extends Exception { // won't compile
//    }
}

abstract class Processor<T extends Throwable> {
    abstract void process() throws T; // OK

    // OK
    public <T extends Exception> void throwIt(T t) throws T {
        throw t;
    }

    public void catchIt() {
        try {
            throwIt(new Exception());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public <T extends Exception> void catchIt(T t) {
//        try {
//            throwIt(t);    // fine
//        } catch (T e) {  // compile error
//        }
//    }

    private T t; // OK

    // private static T t1; // compile error

    void doWork() {
//        try {
//            process();
//        } catch (T t) { // compile error
//            t.printStackTrace();
//        }
    }

    void doThrow(T except) throws T {
        throw except; // ok
    }

    // Cannot be referenced from the static context
//    static void doThrow(T except) throws T {
//        throw except;
//    }
}

abstract class Seq<E> implements List<E> {

//    static <T> boolean isSeq(List<T> x) {
//        return x instanceof Seq<T>; //compile error
//    }
//
//    static boolean isElement(Object x) {
//        return x instanceof E; // compile error
//    }

    static boolean isSeq(Object x) {
        return x instanceof Seq; // OK
    }

    static boolean isSeqArray(Object x) {
        return x instanceof Seq[]; // OK
    }
}

