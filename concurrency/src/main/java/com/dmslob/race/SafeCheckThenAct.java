package com.dmslob.race;

public class SafeCheckThenAct {

    private int number;

    public synchronized void changeNumber() {
        if (number == 0) {
            System.out.println(Thread.currentThread().getName() + " | Changed");
            number = -1;
        } else {
            System.out.println(Thread.currentThread().getName() + " | Not changed");
        }
    }

    public static void main(String[] args) {
        final SafeCheckThenAct checkAct = new SafeCheckThenAct();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> checkAct.changeNumber(), "T" + i).start();
        }
    }
}
