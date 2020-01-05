package com.dmslob.oca;

public class OperatorsMain {

    public static void main(String[] args) {
        //t2();
        //t3();
        //t4();
        //t5();
        //t6();
        //t7();
        //t9();


        boolean x = true, z = true;
        int y = 20;
        x = (y != 10) ^ (z = false);
        System.out.println(x + ", " + y + ", " + z); //true, 20, false

        for (int i = 0; i < 10; ) {
            //i = i++; // always 0, infinite loop
            i++; // OK
            System.out.println(i);
        }

        int x1 = 50, x2 = 75;
        boolean b = x1 >= x2;
        System.out.println(b);

        label();
    }

    static void label() {
        int count = 0;
        ROW_LOOP:
        for (int row = 1; row <= 3; row++)
            for (int col = 1; col <= 2; col++) {
                if (row * col % 2 == 0) continue ROW_LOOP;
                count++;
            }
        System.out.println(count);

    }

    static void t1() {
        int x = 2 * 5 + 3 * 4 - 8;
        System.out.println(x);

        int y = 2;
        y += 5 * 5;
        System.out.println(y);

        System.out.println(9 / 3); // Outputs 3
        System.out.println(9 % 3); // Outputs 0
        System.out.println(10 / 3); // Outputs 3
        System.out.println(10 % 3); // Outputs 1
        System.out.println(11 / 3); // Outputs 3
        System.out.println(11 % 3); // Outputs 2
        System.out.println(12 / 3); // Outputs 4
        System.out.println(12 % 3); // Outputs 0

        short sh = 32767;
        sh += 6;
        sh++;
        //short nw = sh + 56;
        System.out.println(sh);
    }

    static void t2() {
        int x = 1;
        long y = 33;
        //int r = x * y;
        long r = x * y;
        System.out.println(r);

        double d = 39.21;
        //float f = 2.1; compile error
        float f = 2.1f;
        //float fr = d * f;
        double dr = d * f;

        short shx = 10;
        short shy = 3;
        //short shr = shx / shy;
        short shr = (short) (shx / shy);
        int ihr = shx / shy;
    }

    static void t3() {
        short shx = 14;
        float fy = 13;
        double dz = 30;
        double dr = shx * fy / dz;
        System.out.println(dr);
        //int x = !5; // DOES NOT COMPILE
        //boolean y = -true; // DOES NOT COMPILE
        //boolean z = !0; // DOES NOT COMPILE
    }

    static void t4() {
        int x = 3;
        int y = ++x * 5 / x-- + --x; // 4 * 5 / 4 + 2 or 20 / 4 + 2
        System.out.println("x is " + x); // 2
        System.out.println("y is " + y); // 7

        int ix = (int) 1.0;
        System.out.println(ix);

        short shy = (short) 1921222; // Stored as 20678
        System.out.println(shy);

        int iz = (int) 9l;
        System.out.println(iz);

        long lt = 192301398193810323L;
        System.out.println(lt);
    }

    static void t5() {
        int x = 2, z = 3;
        x = x * z; // Simple assignment operator
        x *= z; // Compound assignment operator

        long lx = 10;
        int iy = 5;
        //iy = iy * lx; // DOES NOT COMPILE
        iy *= lx;
        System.out.println(iy);

        boolean bx = false || (2 < 4);
        System.out.println(bx);
        boolean bxx = false | (2 < 4);
        System.out.println(bxx);

        Object ox = null;
        if (ox != null && ox.getClass() != null) {
            // Do something
        }

        int xa = 6;
        boolean by = (xa >= 6) || (++xa <= 7);
        System.out.println(xa);

        long xx = 5;
        int yy = (int) (2 * xx);

    }

    static void t6() {
        //boolean x = true == 3; // DOES NOT COMPILE
        //boolean y = false != "Giraffe"; // DOES NOT COMPILE
        //boolean z = 3 == "Kangaroo"; // DOES NOT COMPILE

        int x = 1;
//        if(x = 5) { // DOES NOT COMPILE
//        }

        int dayOfWeek = 5;
        switch (dayOfWeek) {
            default:
                System.out.println("Weekday");
                break;
            case 0:
                System.out.println("Sunday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
        }
    }

    private int getSortOrder(String firstName, final String lastName) {
        String middleName = "Patricia";
        final String suffix = "JR";
        int id = 0;
        switch (firstName) {
            case "Test":
                return 52;
//            case middleName: // DOES NOT COMPILE
//                id = 5;
//                break;
            case suffix:
                id = 0;
                break;
//            case lastName: // DOES NOT COMPILE
//                id = 8;
//                break;
//            case 5: // DOES NOT COMPILE
//                id = 7;
//                break;
//            case 'J': // DOES NOT COMPILE
//                id = 10;
//                break;
//            case java.time.DayOfWeek.SUNDAY: // DOES NOT COMPILE
//                id = 15;
//                break;
        }
        return id;
    }

    static void t7() {
        int x = 2;
        int y = 5;
//        while (x < 10) { // infinite loop.
//            y++;
//        }

        int xx = 0;
        do {
            xx++;
        } while (false);
        System.out.println(xx); // Outputs 1
    }

    static void t8() {
        int x = 0;
        for (long y = 0, z = 4; x < 5 && y < 10; x++, y++) {
            System.out.print(y + " ");
        }
        System.out.print(x);
    }

    static void t9() {
        //int x = 0;
        for (long y = 0, x = 4; x < 5 && y < 10; x++, y++) { // DOES NOT COMPILE
            System.out.print(x + " ");
        }

//        for(long y = 0, int x = 4; x < 5 && y < 10; x++, y++) { // DOES NOT COMPILE
//            System.out.print(x + " ");
//        }

        String[] names = new String[3];
        names[0] = "one";
        for (String name : names) { // DOES NOT COMPILE
            System.out.print(name + " ");
        }

        System.out.println();
        int[][] myComplexArray = {{5, 2, 1, 3}, {3, 9, 8, 9}, {5, 7, 12, 7}};
        OUTER_LOOP:
        System.out.println("OUTER_LOOP");
        for (int[] mySimpleArray : myComplexArray) {
            INNER_LOOP:
            System.out.println("INNER_LOOP");
            for (int i = 0; i < mySimpleArray.length; i++) {
                System.out.print(mySimpleArray[i] + "\t");
            }
            System.out.println();
        }


    }
}
