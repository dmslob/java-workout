package com.dmslob.guardedblocks;

import java.util.Random;

public class Producer implements Runnable {

    private Drop drop;

    private String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
    };

    Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        drop.put("DONE");
    }
}
