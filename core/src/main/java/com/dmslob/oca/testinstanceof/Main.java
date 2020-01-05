package com.dmslob.oca.testinstanceof;

public class Main {

    public static void main(String[] args) {
        A oa = new A();
        B ob = new B();
        C oc = new C();

        if (ob instanceof A) {
            System.out.println("A");
        }
        if (oc instanceof A) {
            System.out.println("A");
        }
//        if (ob instanceof C) { // compile error B is not C
//            System.out.println(A);
//        }

        AII aii = new AII();
        if (aii instanceof II) {
            System.out.println("II");
        }

        if (oa instanceof II) {
            System.out.println("II");
        }
    }
}

class A {

}

class B extends A {

}

class C extends A {

}

interface II {
}

class AII implements II {
}

class BII implements II {
}