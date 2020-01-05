package com.dmslob.oca;

public class VarargsMain {

    public static void main(String[] args) {
        walk2(1); // 0
        walk2(1, 2); // 1
        walk2(1, 2, 3); // 2
        walk2(1, new int[]{3, 6}); // 2
        //walk2(1, null); //java.lang.NullPointerException

        walk5(1, 2, 3);
        walk5(new int[]{1});
    }

    void walk1(int... nums) {

    }

    static void walk2(int start, int... nums) {
        System.out.println(nums.length);
    }

//    public void walk3(int... nums, int start) {
//    } // DOES NOT COMPILE

//    public void walk4(int... start, int... nums) {
//    } // DOES NOT COMPILE

    public void walk5(int[] start, int... nums) {
    }

    public static void walk5(int... nums) {
        System.out.println(nums.length);
    }

//    public void walk6(int... start, int[] nums) {
//    } // DOES NOT COMPILE
}

interface F {

    static void test() {
    }

    default void a() {
    }
}

interface G extends F {

    default void test() {
    }

//    static void a() {
//    } // compile error
}