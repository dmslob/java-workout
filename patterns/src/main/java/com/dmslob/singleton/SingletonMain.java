package com.dmslob.singleton;

public class SingletonMain {

    public static void main(String[] args) {
        System.out.println(BPS.test());
    }
}

// Lazy initialization with Double check locking
class DCS {
    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static DCS instance;

    private DCS() {
        // private constructor
    }

    public static DCS getInstance() {
        if (instance == null) {
            synchronized (DCS.class) {
                if (instance == null) {
                    instance = new DCS();
                }
            }
        }
        return instance;
    }
}

// Bill Pugh Singleton
class BPS {

    private BPS() {
        System.out.println("private constructor");
        // private constructor
    }

    // Inner class to provide instance of class
    private static class BillPughSingleton {
        static {
            System.out.println("Inner class init...");
        }
        private static final BPS INSTANCE = new BPS();
    }

    public static BPS getInstance() {
        System.out.println("Getting instance");
        return BillPughSingleton.INSTANCE;
    }

    public static String test() {
        return "test";
    }
}