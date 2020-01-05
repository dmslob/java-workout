package com.dmslob.oca.oop;

public class Bird implements Flyer {

    public String name;

    public Bird(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
