package com.dmslob.overriding;

import org.junit.Test;

public class OverridingTest {
    @Test
    public void should_use_overridden_method() {
        add(10.0, null);
    }

    private void add(double d1, double d2) {
        System.out.println("1");
    }

    private void add(Double d1, Double d2) {
        System.out.println("2");
    }
}
