package com.dmslob.optional;

import java.util.Optional;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
