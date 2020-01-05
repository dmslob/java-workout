package com.dmslob.oca.labels;

import java.util.Arrays;

public class LabelMain {

    public static void main(String[] args) {
        int i = 0;
        loop:
        {
            System.out.println("Loop Lable line");
            try {
                System.out.println("try");
                for (; true; i++) {
                    System.out.println("for");
                    if (i > 5) {
                        System.out.println("break");
                        break loop;       // 2
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception in loop.");
            } finally {
                System.out.println("In Finally");      // 3
            }
        }

        int f = new LabelMain().m1(7);
        System.out.println(f);

        continueTest();

        START:
        {
            System.out.println("start");
            for (; ; ) {
                if (true) {
                    break START;
                }
            }
        }

        END:
        //System.out.println(); // compile eroor
        for (; ; ) {
            if (true) {
                break END;
            }
        }
    }

    public int m1(int a) {
        return a * 10 / 4 - 30;
    }

    void crazyLoop() {
        int c = 0;
        JACK:
        while (c < 8) {
            JILL:
            System.out.println(c);
            //if (c > 3) break JILL; else c++; // compile error - undefined label JILL
            if (c > 3) break JACK;
            else c++;
        }
    }

    public void method1(int i) {
        int j = (i * 30 - 2) / 100;

        POINT1:
        for (; j < 10; j++) {
            boolean flag = false;
            while (!flag) {
                if (Math.random() > 0.5) break POINT1;
            }
        }
        POINT2:
        while (j > 0) {
            System.out.println(j--);
            if (j == 4) break POINT2;
        }
    }

    static void continueTest() {
        int[][] intArr = {{1, -2, 3}, {0, 3}, {1, 2, 5}, {9, 2, 5}};

        process:
        for (int i = 0; i < intArr.length; i++) {
            boolean allPositive = true;
            for (int j = 0; j < intArr[i].length; j++) {
                if (intArr[i][j] < 0) {
                    allPositive = false;
                    continue process;
                }
            }
            if (allPositive) {
                // process the array
                System.out.println("Processing the array of all positive ints. " + Arrays.toString(intArr[i]));
            }
            allPositive = true;
        }
    }
}

class A {

    public double m1(int a) {
        return a * 10 / 4 - 30;
    }
}

class A2 extends A {

    public double m1(int a) {
        return a * 10 / 4.0;
    }
}