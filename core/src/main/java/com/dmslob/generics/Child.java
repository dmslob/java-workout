package com.dmslob.generics;

public class Child extends Parent<String> {
    @Override
    String x() {
        return "abc";
    }
}

abstract class Kid<K> extends Parent<String> {
    abstract K xx();
}

// generic class may not extend Throwable
// won't compile
//class MyException<T> extends Exception { }
