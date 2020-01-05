package com.dmslob.oca.oop;

public class ClassTest {

    public static void main(String[] args) {

    }

    public void walk1() {
    }

    public final void walk2() {
    }

    public static final void walk3() {
    }

    public final static void walk4() {
    }

    //public modifier void walk5() {} // DOES NOT COMPILE

    //public void final walk6() {} // DOES NOT COMPILE

    final public void walk7() {
    }

    public Object walk8() {
        return new Exception();
    }

    short getShort() {
        return 32767;
    }

    long getLong() {
        return 9L;
    }
}
