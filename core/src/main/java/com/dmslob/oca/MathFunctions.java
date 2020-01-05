package com.dmslob.oca;

public class MathFunctions {

    static void addToInt(int x, int amountToAdd) {
        x = x + amountToAdd;
    }

    static void addToHolder(int x, int amountToAdd, ValueHolder holder) {
        x = x + amountToAdd;
        holder.setA(x);
    }

    public static void main(String[] args) {
        int a = 15;
        int b = 10;
        MathFunctions.addToInt(a, b);
        System.out.println(a);

        ValueHolder valueHolder = new ValueHolder(5);
        System.out.println(valueHolder.getA());
        MathFunctions.addToHolder(a, b, valueHolder);
        System.out.println(valueHolder.getA());
    }
}

class ValueHolder {

    private int a;

    ValueHolder(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}