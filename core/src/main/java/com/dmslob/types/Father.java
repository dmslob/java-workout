package com.dmslob.types;

public class Father {
    public static void main(String[] args) {
        Son.sayHello();
        Son.swear();
    }

    private String name;

    protected static void swear() {
        System.out.println("swearing");
    }

    public static void sayHello() {
        System.out.println("Hello son");
    }

    public void sayHello(String param) {

    }
}

class Son extends Father {
    public static void sayHello() {
        System.out.println("Hello Father");
    }
}