package com.dmslob.generic;

import java.util.ArrayList;
import java.util.List;

public class SuperExample {

    public static void main(String[] args) {
        List<C1> c1List = new ArrayList<>();
        List<C2> c2List = new ArrayList<>();
        List<C3> c3List = new ArrayList<>();

        print(c1List);
        print(c2List);
        print(c3List);

        printList(c1List);
        printList(c2List);
        //printList(c3List); // compile error, cannot be applied

        List<A> aList = new ArrayList<>();
        List<B> bList = new ArrayList<>();
        List<C> cList = new ArrayList<>();
        printIList(aList);
        printIList(bList);
        printIList(cList);
    }

    // C2 and List of all parent classes of C2
    public static void printList(List<? super C2> list) {
        System.out.println("" + list);
    }

    public static void printIList(List<? super C> list) {
        System.out.println("" + list);
    }

    public static void print(List<? extends C1> list) {
        System.out.println("" + list);
    }
}

class C1 {
}

class C2 extends C1 {
}

class C3 extends C1 {
}

interface A {
}

interface B {
}

interface C extends A, B {
}
