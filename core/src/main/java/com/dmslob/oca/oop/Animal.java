package com.dmslob.oca.oop;

public class Animal {

    private int age;

    public Animal(int age) {
        super();
        this.age = age;
    }

    public static void main(String[] args) {
        new Zebra(4);
    }
}

class Zebra extends Animal {

    public Zebra(int age) {
        super(age);
    }

    public Zebra() {
        this(4);
    }
}

class Mammal {
    public Mammal(int age) {
    }
}

class Elephant extends Mammal {

    public Elephant() {
        super(0);
    }

    public Elephant(int age) {
        super(age);
    }
}
