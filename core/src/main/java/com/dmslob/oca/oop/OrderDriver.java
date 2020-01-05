package com.dmslob.oca.oop;

public class OrderDriver {

    public static void main(String[] args) {
        System.out.print(Order.res + " ");
        System.out.print(Order.res + " ");

        new Order();
        new Order();
        System.out.print(Order.res + " ");
        // u u ucrcr
    }
}

class Order {

    static String res = "";

    {
        res += "c";
    }

    static {
        res += "u";
    }

    {
        res += "r";
    }
}