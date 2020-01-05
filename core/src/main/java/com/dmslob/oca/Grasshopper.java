package com.dmslob.oca;

public class Grasshopper {

    private String name;

    public Grasshopper(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Grasshopper{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Grasshopper one = new Grasshopper("g1");
        Grasshopper two = new Grasshopper("g2");

        System.out.println(one);
        System.out.println(two);

        one = two;
        System.out.println(one);
        System.out.println(two);

        two = null;
        System.out.println(one);
        System.out.println(two);

        one = null;
        System.out.println(one);
        System.out.println(two);
    }
}
