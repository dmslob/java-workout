package com.dmslob.serialization.serializationproxy;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 9140203997753929147L;
    private String name;
    private int id;
    private int age;

    // Constructor
    Person(String name, int id, int age) {
        System.out.println("In Constructor with args");
        this.name = name;
        this.id = id;
        this.age = age;
    }

    // no-arg Constructor
    Person() {
        System.out.println("no-arg constructor");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    /**
     * writeReplace method for the proxy pattern
     */
    private Object writeReplace() {
        System.out.println("In writeReplace() method");
        return new PersonProxy(this);
    }

    private static class PersonProxy implements Serializable {

        private static final long serialVersionUID = -5965328891170223339L;
        private String name;
        private int id;
        private int age;

        PersonProxy(Person p) {
            this.name = p.name;
            this.id = p.id;
            this.age = p.age;
        }

        // readResolve method for Person.PersonProxy
        private Object readResolve() {
            System.out.println("In readResolve() method");
            return new Person(name, id, age); // Uses public constructor
        }
    }
}
