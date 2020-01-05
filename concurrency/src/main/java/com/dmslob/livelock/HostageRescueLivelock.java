package com.dmslob.livelock;

public class HostageRescueLivelock {

    private static final Police police = new Police();

    private static final Criminal criminal = new Criminal();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> police.giveRansom(criminal));
        t1.start();

        Thread t2 = new Thread(() -> criminal.releaseHostage(police));
        t2.start();
    }
}
