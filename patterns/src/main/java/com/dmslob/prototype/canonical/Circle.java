package com.dmslob.prototype.canonical;

public class Circle implements Figure {

    private int radix;

    public Circle(int radix) {
        this.radix = radix;
    }

    public void changeRadix(int delta) {
        radix += delta;
    }

    public double getSquare() {
        return Math.PI * radix * radix;
    }

    public Circle clone() {
        return new Circle(radix);
    }
}
