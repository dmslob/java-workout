package com.dmslob.singleton;

public class SingletonMain {

    public static void main(String[] args) {

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
        // private constructor
    }

    // Inner class to provide instance of class
    private static class BillPughSingleton {
        private static final BPS INSTANCE = new BPS();
    }

    public static BPS getInstance() {
        return BillPughSingleton.INSTANCE;
    }
}