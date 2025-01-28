package com.dmslob.generics;

public class Ex2<T extends Runnable, String> {
    //String s = "Hello"; //compile error

    java.lang.String s = "Hello";

    public void test(T t) {
        t.run();
    }
}
