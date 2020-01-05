package com.dmslob.cas;

public class CasExample {

    public static void main(String[] args) {

    }
}

class MyInteger {

    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    private boolean compareAndSet(int oldValue, int newValue) {
        if (value == oldValue) {
            value = newValue;
            return true;
        }
        return false;
    }

    // CAS has three operands - a memory location V on which to operate, the expected old value A, and the newvalue B.
    // CAS atomically updates V to the new value B, but only if the value in V matches the expected old value A;
    // otherwise it does nothing. In either case, it returns the value currently in V.
    public final long incrementAndGet() {
        for (; ; ) {
            int oldValue = get();
            int newValue = oldValue + 1;
            if (compareAndSet(oldValue, newValue)) {
                return newValue;
            }
        }
    }
}