package com.dmslob.oop.inheritance;

public class Derived extends ParentTest{
    public static void main(String[] args) {
        ParentTest parentTest = new Derived();
        parentTest.first();
    }

    public void first() {
        System.out.println("Derived");
    }
}

class ParentTest {
    public void first() {
        System.out.printf("ParenFirst");
    }
}