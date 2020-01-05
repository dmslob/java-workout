package com.dmslob.generic.constructor;

import java.io.Serializable;

public class GenericConstructorDemo {

    // we can also have a constructor with a generic type that’s different from the class’ generic type
    public static void main(String[] args) {

        Entry entry1 = new Entry("sample", 1);
        System.out.println(entry1.getData());
        System.out.println(entry1.getRank());

        Product product = new Product("milk", 2.5);
        product.setSales(30);

        Entry entry2 = new Entry(product);
        System.out.println(product.toString() + ", " + entry2.getData());
        System.out.println(entry2.getRank());

        GenericEntry<String> genericEntry = new GenericEntry<>(1);

        System.out.println(genericEntry.getData());
        System.out.println(genericEntry.getRank());

        GenericEntry<String> stringGenericEntry = new GenericEntry<>("sample", 1);
        System.out.println(stringGenericEntry.getData());
        System.out.println(stringGenericEntry.getRank());

        GenericEntry<Integer> iGenericEntry = new GenericEntry<>(Integer.valueOf(12), 1);
        System.out.println(iGenericEntry.getData());
        System.out.println(iGenericEntry.getRank());
    }
}

class Entry {
    private String data;
    private int rank;

    public Entry(String data, int rank) {
        this.data = data;
        this.rank = rank;
    }

    public <E extends Rankable & Serializable> Entry(E element) {
        this.data = element.toString();
        this.rank = element.getRank();
    }

    public String getData() {
        return data;
    }

    public int getRank() {
        return rank;
    }
}

interface Rankable {
    int getRank();
}

class Product implements Rankable, Serializable {
    private String name;
    private double price;
    private int sales;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int getRank() {
        return sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}

class GenericEntry<T> {
    private T data;
    private int rank;

    public GenericEntry(int rank) {
        this.rank = rank;
    }

    public GenericEntry(T data, int rank) {
        this.data = data;
        this.rank = rank;
    }

    public T getData() {
        return data;
    }

    public int getRank() {
        return rank;
    }
}