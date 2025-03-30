package com.dmslob.types;

public final class ThreeBooleans {
    private ThreeBooleans() {
    }

    public static boolean xOrMoreAreTrueByLoop(boolean[] bools, int x) {
        int count = 0;
        for (boolean i : bools) {
            count += i ? 1 : 0;
            if (count >= x) {
                return true;
            }
        }
        return false;
    }

    public static boolean twoOrMoreAreTrueByOperators(boolean a, boolean b, boolean c) {
        return (a && b) || (a && c) || (b && c);
    }

    public static boolean twoOrMoreAreTrueByKarnaughMap(boolean a, boolean b, boolean c) {
        return (c && (a || b)) || (a && b);
    }

    public static boolean twoOrMoreAreTrueByXor(boolean a, boolean b, boolean c) {
        return a ^ b ? c : a;
    }
}
