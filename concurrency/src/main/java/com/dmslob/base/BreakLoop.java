package com.dmslob.base;

public class BreakLoop {

    public static void main(String[] args) {
        BreakLoop breakLoop = new BreakLoop();
        breakLoop.breakOuterLoop();
        breakLoop.breakInnerLoop();
    }

    void breakOuterLoop() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 2) {
                    System.out.println("Breaking");
                    return;
                }
                System.out.println(i + " " + j);
            }
        }
    }

    void breakInnerLoop() {
        outerloop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i * j > 6) {
                    System.out.println("Breaking");
                    break outerloop;
                }
                System.out.println(i + " " + j);
            }
        }
        System.out.println("Done");
    }
}
