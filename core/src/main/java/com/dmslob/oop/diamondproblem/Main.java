package com.dmslob.oop.diamondproblem;

/**
 * Created by Dmytro_Slobodenyuk on 9/12/2018.
 */

public class Main {

    public static void main(String[] args) {
        new ABImpl().foo();
        new SubClass().foo();
    }
}

interface A {
    default void foo() {
        System.out.println("interface A");
    }
}

interface B {
    default void foo() {
        System.out.println("interface B");
    }
}

// the compiler will issue an error
class ABImpl implements A, B {
    // resolve the conflict manually
    public void foo() {
        A.super.foo();
    }
}

// the method definition in the class is used and the interface definition is ignored.
class BaseClass {
    public void foo() {
        System.out.println("BaseClass's foo");
    }
}

interface BaseInterface {
    default public void foo() {
        System.out.println("BaseInterface's foo");
    }
}

class SubClass extends BaseClass implements BaseInterface {

}