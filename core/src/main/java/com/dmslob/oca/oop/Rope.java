package com.dmslob.oca.oop;

public class Rope {

    public static int length = 0;

    public static void swing() {
        System.out.println("swing");
    }


    public void climb() {
        System.out.println("climb");
    }

    public static void play() {
        swing();
        // climb(); // compile error
    }

    public static void main(String[] args) {
        Rope rope = new Rope();
        rope.play();
        Rope rope2 = null;
        rope2.play();
    }
}
