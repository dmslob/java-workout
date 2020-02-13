package com.dmslob.switchblock;

public class SwitchDemo {

    public static void main(String[] args) {
        An an = new An() {
            @Override
            public void me() {
                System.out.println("me");
            }
        };
        an.me();
    }
}

interface Animal {
    default void me() {

    }
}

abstract class An implements Animal {
}