package com.dmslob.oca;

public class OOPMain {

    private static String h;

    public OOPMain() {
        h = "fgfdg";
    }

    public static void main(String[] args) {
        Car o1 = new CrachedSportCar();
        SportCar o2 = (SportCar) o1;

        System.out.println(o1.m1());
        System.out.println(o2.i);

        int r = (int) Math.round(-0.5);
        System.out.println(r);

        char[] myCharArr = {'g', 'o', 'o', 'd'};
        String newStr = null;
        for (char ch : myCharArr) {
            newStr = newStr + ch;
        }
        System.out.println(newStr);

        int i = 1, j = 10;
        do {
            System.out.println("i=" + i);
            System.out.println("j=" + j);
            if (i++ > --j) continue;
        } while (i < 5);
        System.out.println("i=" + i + " j=" + j);
    }
}

class Car {
    int i = 10;

    int m1() {
        return i;
    }
}

class SportCar extends Car {
    int i = 20;

    int m1() {
        return i;
    }
}

class CrachedSportCar extends SportCar {
    int i = 30;

    int m1() {
        return i;
    }

    void test() {
    }

    int test(int a) {
        return a;
    }

    double test(double d) {
        return d;
    }

}