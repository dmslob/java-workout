package com.dmslob.race;

public class UnsafeCheckThenAct {

    private int number;

    public void changeNumber() {
        if (number == 0) {
            System.out.println(Thread.currentThread().getName() + " | Changed");
            number = -1;
        } else {
            System.out.println(Thread.currentThread().getName() + " | Not changed");
        }
    }

    public static void main(String[] args) {

        final UnsafeCheckThenAct checkAct = new UnsafeCheckThenAct();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> checkAct.changeNumber(), "T" + i).start();
        }
    }
}
