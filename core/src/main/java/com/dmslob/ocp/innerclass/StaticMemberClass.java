package com.dmslob.ocp.innerclass;


public class StaticMemberClass {

    public static void main(String[] args) {
        EnclosingClass.EnclosedClass.accessEnclosingClass();
        EnclosingClass.EnclosedClass ec = new EnclosingClass.EnclosedClass();
        ec.accessEnclosingClass2();
    }
}
