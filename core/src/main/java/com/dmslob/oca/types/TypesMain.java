package com.dmslob.oca.types;

public class TypesMain {

    public static void main(String[] args) {
        ints();
        testBasicTypes();
        equality();
    }

    static void ints() {
        int octal = 017;
        System.out.println(octal);

        int hex = 0xAF;
        System.out.println(hex);

        int binary = 0b10;
        System.out.println(binary);
    }

    static void switchTypes() {
        byte by = 127;
        final byte b = 12;
        switch (by) {
            default:
                break;
            //case 129:
            case 127:
                break;
            case b:
                break;
            // default:
        }
    }

    static void equality() {
        int a = 12;
        double db = 12;
        if (a == db) {
            System.out.println("OK");
        }

        double d = 12.0;
        float fl = 12.0F;
        if (d == fl) {
            System.out.println("OK");
        }

        double dbl = 12;
        char ch = 12;
        if (dbl == ch) {
            System.out.println("OK");
        }

        int x = 1;
        boolean b = true;
        // if (x == b) // compile error
    }

    static void testBasicTypes() {
        //float value = 102.0; // incompatible types float and double
        float value1 = 102;
        System.out.println(value1);

        float value2 = (int) 102.0;
        System.out.println(value2);

        //float value3 = 1f * 0.0; // incompatible types float and double
        float value4 = 1f * (short) 0.0;
        System.out.println(value4);
        //float value5 = 1f * (boolean) 0; // cannot cast int to boolean
        //float value6 = 102.0 + value1; // incompatible types float and double
        double d = 102.0 + value1;
        float i = 5 + 5f;
        System.out.println(i);
    }
}

class Test {
    private int a = 56;
}
