package com.dmslob.oca.oop;

public class VarsTest {

    public static void main(String[] args) {
        V1 v1 = new V2();
        System.out.println(v1.a); // 10
        System.out.println(((V2) v1).a); // 11
        System.out.println(((V2) v1).getB());
    }
}

class V1 {

    public int a = 10;
    private int b = 11;
}

class V2 extends V1 {

    public int a = 11;
    private int b = 12;

    public int getB() {
        return this.b;
    }
}
