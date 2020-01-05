package com.dmslob.oca.oop;

public class B extends A {

    int i = 4;

    public static void main(String[] args) {
        A a = new B();
        a.print(); // 0 4
    }

    void print() {
        System.out.println(i);
    }

}

class A {

    public A() {
        print();
    }

    void print() {
        System.out.println("A");
    }
}