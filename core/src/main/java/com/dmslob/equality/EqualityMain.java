package com.dmslob.equality;

public class EqualityMain {

    public static void main(String[] args) {
        EqualityMain equalityMain = new EqualityMain();

        equalityMain.instanceOf();
        equalityMain.assignableFrom();
        equalityMain.difference();
    }

    void instanceOf() {
        Object theObject = "test";
        if (theObject instanceof String) {
            System.out.println("theObject is instance of String");
        }
        Object nullObject = null;
        if (nullObject instanceof String) {
            System.out.println("nullObject is instance of String");
        } else {
            System.out.println("nullObject is not instance of String");
        }

        B b = new C();
        if (b instanceof A) { // true
            System.out.println("b instanceof A");
        }
        if (b instanceof B) { // true
            System.out.println("b instanceof B");
        }
        if (b instanceof C) { // true
            System.out.println("b instanceof C");
        }
        if (b instanceof D) { // false
            System.out.println("b instanceof D");
        }

        //if (b instanceof Integer) {} // compile error
        if (b.getClass().isAssignableFrom(int.class)) {
            System.out.println("b is assignable from int");
        }
    }

    void assignableFrom() {
        System.out.println();

        B b = new C();
        Object obj = new C();
        //if (b instanceof obj.getClass()) {} // compile error
        if (obj.getClass().isAssignableFrom(b.getClass())) {
            System.out.println("obj.getClass() isAssignableFrom b.getClass()");
        }
        if (A.class.isAssignableFrom(b.getClass())) { // true
            System.out.println("A.class isAssignableFrom b.getClass()");
        }

        if (B.class.isAssignableFrom(b.getClass())) { // true
            System.out.println("B.class isAssignableFrom b.getClass()");
        }

        if (C.class.isAssignableFrom(b.getClass())) { // true
            System.out.println("C.class isAssignableFrom b.getClass()");
        }

        if (D.class.isAssignableFrom(b.getClass())) { // false
            System.out.println("D.class isAssignableFrom b.getClass()");
        }
    }

    void difference() {
        System.out.println();
        B b = new B();
        if (Object.class.isAssignableFrom(B.class)) {
            System.out.println("Object.class.isAssignableFrom(B.class)");
        }

        if (b instanceof Object) {
            System.out.println("b instanceof Object");
        }
    }
}

class A {
}

class B extends A {
}

class C extends B {
}

class D extends C {
}

interface IFace {
}

class AA implements IFace {
}

class BB extends A {
}

class CNum extends java.math.BigInteger implements IFace {
    public CNum(String s) {
        super(s);
    }
}
