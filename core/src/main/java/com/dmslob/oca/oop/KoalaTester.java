package com.dmslob.oca.oop;

//import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList; // static import

public class KoalaTester {

    private static final int NUM_SECONDS_PER_HOUR;

    static {
        int numSecondsPerMinute = 60;
        int numMinutesPerHour = 60;
        NUM_SECONDS_PER_HOUR = numSecondsPerMinute * numMinutesPerHour;
    }

    public static void main(String[] args) {
        List<String> list = asList("one", "two"); // no Arrays.
        //Arrays.asList("one"); // DOES NOT COMPILE

        Koala.main(new String[0]);

        Koala k = new Koala();
        System.out.println(k.count);
        k = null;
        System.out.println(k.count);

        Koala.count = 4;
        Koala koala1 = new Koala();
        Koala koala2 = new Koala();
        koala1.count = 6;
        koala2.count = 5;
        System.out.println(Koala.count);
    }
}
