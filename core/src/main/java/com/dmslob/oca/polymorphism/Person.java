package com.dmslob.oca.polymorphism;

public abstract class Person {

    private void say() {
        System.out.println("Person is saying");
    }

    void test() {}

    public static void main(String[] args) {
        Person thePerson = new BadPerson();
        thePerson.say();

        ((BadPerson) thePerson).say();

        BadPerson badPerson = new BadPerson();
        badPerson.say();
    }
}

class BadPerson extends Person {

    protected void say() {
        System.out.println("BadPerson is saying");
    }

}

interface A {

    default void test() {
        System.out.println("test");
    }

    static void m() {
        System.out.println("m");
    }
}

interface B extends A {

    // static void test() {} // compile error

    default void m() {
        System.out.println("m");
    } // OK
}