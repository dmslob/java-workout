package com.dmslob.oca;

import java.util.ArrayList;
import java.util.List;

public class OcaMain {

    static {
        // throw new NullPointerException();
        // if(true) throw new NullPointerException(); // ExceptionInInitializerError
    }

    public static void main(String[] args) {
        //testTernary(); // Less Than,5
        //testSwitch();
        //testException();
        //testArrays();
        //switchStatement();

        int expr1 = 3 + 5 * 9 - 7;  //41
        int expr2 = 3 + (5 * 9) - 7; //41
        int expr3 = 3 + 5 * (9 - 7); //13
        int expr4 = (3 + 5) * 9 - 7; // 65

        //testReferences();
        //testException2();
        System.out.println(new Boolean("TrUe"));
        int x = 10;
        do {
            x--;
            if (x == -1) break;
            System.out.println(x);  // 1
        } while (x < 10);
    }

    static void testReferences() {
        A1 a = new A1();
        AA1 aa = new AA1();
        a = aa;
        System.out.println("a = " + a.getClass());
        System.out.println("aa = " + aa.getClass());
    }

    static void testException2() {
        int index = 1;
        try {
            getArray()[index = 2]++;
        } catch (Exception e) {
        }  //empty catch
        System.out.println("index = " + index);
    }

    public static int[] getArray() {
        return null;
    }

    static void switchStatement() {
        char ch = 'a';
        //Integer i = ch;

        Integer x = 1;
        // switch ('a') // compile error
        switch (x) {

        }

        byte by = 10;
        switch (by) {
            case 20: //some code;     
                //case 300: //some code; // 300 > 127
        }
    }

    static void testArrays() {
        int[] array = {6, 9, 8};
        List<Integer> list = new ArrayList<>();
        list.add(array[0]);
        list.add(array[2]);
        list.set(1, array[1]);
        list.remove(0);
        System.out.println(list);
    }

    static void testTernary() {
        int x = 0;
        while (++x < 5) {
            x += 1;
        }
        String message = x > 5 ? "Greater than" : "Less Than";
        System.out.println(message + "," + x);
    }

    static void testSwitch() {
        boolean keepGoing = true;
        int count = 0;
        int x = 3;
        while (count++ < 3) {
            int y = (1 + 2 * count) % 3;
            switch (y) {
                default:
                case 0:
                    x -= 1;
                    break;
                case 1:
                    x += 5;
            }
        }
        System.out.println(x);
    }

    static void testException() {
        System.out.print("a");
        try {
            System.out.print("b");
            throw new IllegalArgumentException();
        } catch (RuntimeException e) {
            System.out.print("c");
        } finally {
            System.out.print("d");
        }
        System.out.print("e");
    }
}

class A1 {
}

class AA1 extends A1 {
}

class _C {

    private static int $;

    public static void main() {
        //String a_b;
        //System.out.println($);
        //System.out.println(a_b); // not have been initialized
    }
}

interface HasTail {
    int getTailLength();

    static void m() {
        System.out.println("mmmm");
    }
}

abstract class Puma implements HasTail {

//    protected int getTailLength() {
//        return 4;
//    }
}

//class Cougar extends Puma {
//
//    public static void m() {
//        Puma puma = new Puma();
//        System.out.println(puma.getTailLength());
//    }
//}

