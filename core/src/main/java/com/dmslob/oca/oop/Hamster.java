package com.dmslob.oca.oop;

public class Hamster extends YetMoreInitializationOrder {

    private String color;
    private int weight;

    private String name = "Torchie";

    {
        System.out.println(name);
    }

    private static int COUNT = 0;

    static {
        System.out.println(COUNT);
    }

    {
        COUNT++;
        System.out.println(COUNT);
    }

    public Hamster() {
        System.out.println("Constructor");
    }

    static void getParent() {
        System.out.println("Hamster static");
    }

    public Hamster(int weight) { // first constructor
        this.weight = weight;
        color = "brown";
    }

    public Hamster(int weight, String color) { // second constructor
        this.weight = weight;
        this.color = color;
    }

    public static void main(String[] args) {
        //new Hamster();
        Hamster.getParent();
    }
}

class YetMoreInitializationOrder {

    static {
        add(2);
    }

    static void add(int num) {
        System.out.println("YetMoreInitializationOrder:: " + num + " ");
    }

    YetMoreInitializationOrder() {
        add(5);
    }

    static {
        add(4);
    }

    {
        add(6);
    }

    static {
        new YetMoreInitializationOrder();
    }

    {
        add(8);
    }
}