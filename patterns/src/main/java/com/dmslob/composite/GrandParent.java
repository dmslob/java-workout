package com.dmslob.composite;

import java.util.ArrayList;
import java.util.List;

public class GrandParent implements Human {
    private final String name;
    private final String lastName;
    private final List<Human> children = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public GrandParent(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public void print() {
        System.out.println("==============================");
        System.out.println("Name : " + getName());
        System.out.println("Last name : " + getLastName());
        System.out.println("Children :");
        for (Human child : children) {
            System.out.println("  - Name : " + child.getName());
        }
        System.out.println("==============================");
    }

    @Override
    public void addChild(Human child) {
        children.add(child);
    }

    @Override
    public void addParent(Human parent) {

    }
}
