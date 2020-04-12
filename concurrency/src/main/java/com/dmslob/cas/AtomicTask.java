package com.dmslob.cas;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicTask {
    static AtomicLong i = new AtomicLong();

    static {
        Thread t = new Thread(i::getAndIncrement);
        t.start();
        try {
            t.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] a) {
        System.out.print(i.get());
    }
}
