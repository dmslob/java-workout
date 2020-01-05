package com.dmslob.oca.b;

import com.dmslob.oca.a.AccessTest;
import com.dmslob.oca.oop.*;

public class AccessTester extends AccessTest {

    private static Rope rope1 = new Rope();
    private static Rope rope2 = new Rope();

    {
        System.out.println(rope1.length);
    }

    private static final String bench;

    static {
        bench = "he";
    }

    static {
        //bench = "he"; // complile error
    }

    public static void main(String[] args) {

        AccessTest ref = new AccessTest();
        ref.d();
        //ref.c(); // complile error

        AccessTest accessTester = new AccessTester();
        // accessTester.c(); // compile error
    }
}

interface I {
    void m1();
}

abstract class Klass {
    void m1() {
    }
}

class SubClass extends Klass implements I {
    public void m1() {
    }
}