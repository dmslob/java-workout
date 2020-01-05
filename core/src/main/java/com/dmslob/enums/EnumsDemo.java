package com.dmslob.enums;

public class EnumsDemo {

    public static void main(String[] args) {
        Person jim = Person.JIM;
        jim.speak();

        Person joseph = Person.JOE;
        joseph.speak();
    }
}

interface Speaker {
    void speak();
}

enum Person implements Speaker {
    JOE("JOSEPH"),
    JIM("JAMES");

    private final String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public void speak() {
        System.out.println(name + " is speaking...");
    }
}