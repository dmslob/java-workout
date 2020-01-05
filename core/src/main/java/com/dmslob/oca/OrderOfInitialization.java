package com.dmslob.oca;

import java.util.*;

public class OrderOfInitialization {

    //{ System.out.println(name); } // DOES NOT COMPILE

    private boolean empty;
    private String brand;
    private double db = 0.0;

    private String name = "Fluffy";

    {
        System.out.println("setting field");
    }

    public OrderOfInitialization() {
        name = "Tiny";
        System.out.println("setting constructor");
    }

    public static void main(String[] args) {
        OrderOfInitialization order = new OrderOfInitialization();
        System.out.println(order.name);

        Egg egg = new Egg();
        System.out.println(egg.getNumber());

        declarations();

        System.out.println(order.empty);
        System.out.println(order.brand);
        System.out.println(order.db);
        double amount4 = 0xE;
        System.out.println(amount4);
    }

    void findAnswer(boolean check) {
        int answer;
        int onlyOneBranch;
        if (check) {
            onlyOneBranch = 1;
            answer = 1;
        } else {
            answer = 2;
        }
        System.out.println(answer);
        //System.out.println(onlyOneBranch); // DOES NOT COMPILE
    }

    static void declarations() {
        short numPets = 32767;

        String s1, s2;
        String s3 = "yes", s4 = "no";
        int i1, i2, i3 = 0;
        //int num, String value; // DOES NOT COMPILE

        //int amount1 = 9L;
        int amount2 = 0b101;
        int amount3 = 0xE;
        double amount4 = 0xE;
        //double amount5 = 1_2_.0_0;
        //int amount6 = 1_2_;

        int _f;
        int $i;
        int ii = 1_234;
        double d1 = 1_234.0;
        //int 34hgt; // DOES NOT COMPILE
        //int Int; int Void; int Static; int Astract; int Public; // BAD PRACTICE
        int okidentifier;
        int $OK2Identifier;
        int _alsoOK1d3ntifi3r;
        int __SStillOkbutKnotsonice$;
        //int java.lang;

        //int 3DPointClass // identifiers cannot begin with a number
        // int hollywood@vine // @ is not a letter, digit, $ or _
        //int *$coffee // * is not a letter, digit, $ or _
        //int public // public is a reserved word

        System.out.println();
    }

    static void allowedDigits() {
        //long max = 3123456789; // DOES NOT COMPILE
        long max = 3123456789L; // OK
        int million1 = 1000000;
        int million2 = 1_000_000;

        //double notAtStart = _1000.00; // DOES NOT COMPILE
        //double notAtEnd = 1000.00_; // DOES NOT COMPILE
        //double notByDecimal = 1000_.00; // DOES NOT COMPILE
        double annoyingButLegal = 1_00_0.0_0; // this one compiles

        int oct = 017;
        System.out.println(oct);
        int hex = 0xFF;
        System.out.println(hex);
        int bin = 0b10;
        System.out.println(bin);

        System.out.println(56); // 56
        System.out.println(0b11); // 3
        System.out.println(017); // 15
        System.out.println(0x1F); // 31
    }
}

class Egg {

    public Egg() {
        number = 5;
    }

    private int number = 3;

    {
        number = 4;
    }

    public int getNumber() {
        return number;
    }
}

class Bear {

    protected void finalize() {
        System.out.println("Roar!");
    }
}