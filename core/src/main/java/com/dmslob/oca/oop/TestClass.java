package com.dmslob.oca.oop;

public class TestClass {

    public static void main(String[] args) {
        Flyer f = new Eagle("American Bald Eagle");
        System.out.println(f.getName());
        System.out.println(((Eagle) f).name);
        System.out.println(((Bird) f).getName());

        C1 o1 = new C1();
        C2 o2 = new C2();
        C3 o3 = new C3();

        I1 i1 = o3;
        I2 i2 = (I2) i1;
        I1 b = o3;
    }
}

interface I1 {
}

interface I2 {
}

class C1 implements I1 {
}

class C2 implements I2 {
}

class C3 extends C1 implements I2 {
}

class C4 extends C3 implements I1, I2 {
}