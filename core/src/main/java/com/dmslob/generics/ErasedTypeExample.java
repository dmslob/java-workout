package com.dmslob.generics;

import java.util.*;

class ParentClass<T> {
    public void foo(T t) {
        System.out.println("ParentClass.foo()");
    }
}

class ChildClass extends ParentClass<Double> {
    public void foo(String s) {
        System.out.println("ChildClass.foo()");
    }
}

public class ErasedTypeExample {

    public static void main(String[] args) {

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);

        ChildClass childClass = new ChildClass();
        childClass.foo("f");
        childClass.foo(6.6);
    }
}
