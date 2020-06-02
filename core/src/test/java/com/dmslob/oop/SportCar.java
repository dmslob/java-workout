package com.dmslob.oop;

public class SportCar extends Car {
    @Override
    public String drive() {
        String t = "Sport Car";
        System.out.println(t);
        return t;
    }
}
