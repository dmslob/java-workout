package com.dmslob.synchronizer.phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo {

    static int lines = 10;
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    static StringBuffer randomAlphabet = new StringBuffer();

    public static void main(String[] args) {

        final Phaser phaser = new Phaser() {
            protected boolean onAdvance(int phase, int parties) {
                // John Nash mode
				/* print "deviations"
				for (int i = 0; i < alphabet.length(); i++) {
					System.out.printf("%d ", randomAlphabet.indexOf(
							  alphabet.charAt(i) + "") - i);
				}
				System.out.println();
				*/
                System.out.println("phase: " + phase);
                System.out.println(randomAlphabet);
                randomAlphabet = new StringBuffer();

                return phase >= lines;
            }
        };
        // everyone have to wait for the main thread
        phaser.register();
        for (int i = 0; i < alphabet.length(); i++) {
            final char next = alphabet.charAt(i);
            phaser.register(); // ticket for the following thread
            new Thread(() -> {
                do {
                    randomAlphabet.append(next);
                    phaser.arriveAndAwaitAdvance(); // checkout
                } while (!phaser.isTerminated());
            }).start();
        }
        System.out.println("Write this by hand and carry in the pocket:");
        // some additional preparations may be done here
        // release
        phaser.arriveAndDeregister();
    }
}

class EventCounter {

    private Phaser count = new Phaser(1);

    public void eventOccured() {
        count.arrive();
    }

    public void waitFor(int events) {
        count.register();
        for (int i = 0; i < events; i++) {
            count.arriveAndAwaitAdvance();
        }
        count.arriveAndDeregister();
    }
}

