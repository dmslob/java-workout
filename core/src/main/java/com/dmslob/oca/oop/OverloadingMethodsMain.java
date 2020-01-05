package com.dmslob.oca.oop;

public class OverloadingMethodsMain {

    public static void main(String[] args) {

        //new OverloadingMethodsMain().fly(new int[] { 1, 2, 3 });
        new OverloadingMethodsMain().fly(1, 2, 3);

        new OverloadingMethodsMain().fly(1);
        new OverloadingMethodsMain().fly(new Integer(1));

        new Another().fly("123");
        new Another().fly(123);

        System.out.println("Yet");
        new Yet().fly(123L);
        new Yet().fly(new Integer(2));
        new Yet().fly(123);

        System.out.println("Plane");
        new Plane().fly(3);
        new Plane().fly((short) 3);
        new Plane().fly(3L);
        new Plane().fly(new Integer(2));

        System.out.println("Ref");
        new ReferenceType().fly("string");
        new ReferenceType().fly(123);

        new Primitive().fly(4L);
        new Primitive().fly(4);

        System.out.println("Glinder");
        System.out.print(new Glider().glide("a"));
        System.out.print(new Glider().glide("a", "b"));
        System.out.print(new Glider().glide("a", "b", "c"));
        //new Glider().play(4); // DOES NOT COMPILE
        new Glider().play(4L);

        System.out.println("TheNewer");
        TheNewer theNewer = new TheNewer();
        short s = 123;
        theNewer.print(s);
        theNewer.print(true);
        theNewer.print(6.789);


    }

    public void fly(int numMiles) {
        System.out.println("int");
    }

    public void fly(short numFeet) {
        System.out.println("short");
    }

    public boolean fly() {
        System.out.println("bool");
        return false;
    }

    void fly(int numMiles, short numFeet) {
    }

    public void fly(short numFeet, int numMiles) throws Exception {
    }

    //public void fly(int[] lengths) { }

    public void fly(int... lengths) {
    } // DOES NOT COMPILE if uncomment fly fly(int[] lengths)

    public void fly(Integer numMiles) {
        System.out.println("Integer");
    }
}

class TheNewer {

    public void print(byte b) {
        System.out.println("byte");
    }

    public void print(int i) {
        System.out.println("int");
    }

    public void print(float f) {
        System.out.println("float");
    }

    public void print(Object o) {
        System.out.println("Object");
    }
}

class ReferenceType {

    public void fly(String s) {
        System.out.println("string ");
    }

    public void fly(Object o) {
        System.out.println("object ");
    }
}

class Another {

    public void fly(String s) {
        System.out.println("string ");
    }

    public void fly(Object o) {
        System.out.println("object ");
    }
}

class Yet {

    public void fly(long l) {
        System.out.println("long");
    }

    public void fly(Integer o) {
        System.out.println("Integer");
    }
}

class Primitive {

    public void fly(long l) {
        System.out.println("long");
    }

    public void fly(int l) {
        System.out.println("int");
    }

//    public void fly(Integer o) {
//        System.out.println("Integer");
//    }
}

class Plane {

    public void fly(int numMiles) {
        System.out.println("int");
    }

    public void fly(long numMiles) {
        System.out.println("long");
    }

    public void fly(Integer numMiles) {
        System.out.println("Integer");
    }
    //public int fly(int numMiles) { } // DOES NOT COMPILE

    //public static int fly(int numMiles) { } // DOES NOT COMPILE

    public void fly(short numFeet) {
        System.out.println("short");
    }

    public boolean fly() {
        return false;
    }

    void fly(int numMiles, short numFeet) {
    }

    public void fly(short numFeet, int numMiles) throws Exception {
    }

    static short fly(float f) {
        short t = 67;
        return t;
    }

    public void fly(int[] lengths) {
    }
    //public void fly(int... lengths) { } // DOES NOT COMPILE
}

class Glider {

    public static String glide(String s) {
        return "1";
    }

    public static String glide(String... s) {
        return "2";
    }

    public static String glide(Object o) {
        return "3";
    }

    public static String glide(String s, String t) {
        return "4";
    }

    public void play(Long l) {
        System.out.println("long");
    }

//    public void play(Long... l) {
//        System.out.println("long vararg");
//    }
}

class O1 {

    int get(int a, float g) {
        return 1;
    }

    int get(float g, int a) {
        return 2;
    }
}
