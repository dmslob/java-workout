package com.dmslob.oca;

public class BearOrShark {

    public static void main(String[] args) {
        int luck = 10;
        if((luck>10 ? luck++: --luck)<10) {
            System.out.print("Bear");
        } if(luck<10) System.out.print("Shark");

        AnotherJerk jerk = new AnotherJerk();
        jerk.test();

    }
}

interface Jerk {
    default void test() {
        System.out.println("test");
    }
}

class AnotherJerk implements Jerk {

}
