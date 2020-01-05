package com.dmslob.jmx;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator implements CalculatorMBean {

    private int decimalPlaces = 2;

    public double add(double first, double second) {
        BigDecimal bdFirst = new BigDecimal(first);
        BigDecimal bdSecond = new BigDecimal(second);

        BigDecimal bdSum = bdFirst.add(bdSecond);
        return bdSum.setScale(decimalPlaces, RoundingMode.HALF_UP).doubleValue();
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }
}
