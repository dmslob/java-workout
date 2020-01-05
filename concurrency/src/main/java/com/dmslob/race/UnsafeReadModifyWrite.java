package com.dmslob.race;

public class UnsafeReadModifyWrite {

    private int number;

    public void incrementNumber() {
        number++;
    }

    public int getNumber() {
        return this.number;
    }

    public static void main(String[] args) throws InterruptedException {

        final UnsafeReadModifyWrite rmw = new UnsafeReadModifyWrite();

        for (int i = 0; i < 1_000; i++) {
            new Thread(() -> rmw.incrementNumber(), "T" + i).start();
        }

        Thread.sleep(6000);
        System.out.println("Final number (should be 1_000): " + rmw.getNumber());
    }
}
