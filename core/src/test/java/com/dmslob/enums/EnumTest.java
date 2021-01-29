package com.dmslob.enums;

import org.junit.Assert;
import org.junit.Test;

public class EnumTest {

    @Test
    public void shouldBeEqualsWhenComparingByValueOf() {
        Planet expectedPlanet = Planet.MERCURY;
        Planet actualPlanet = Planet.valueOf("MERCURY");

        Assert.assertEquals(expectedPlanet, actualPlanet);
    }

    @Test
    public void shouldGetEnumByValue() {
        Planet expectedPlanet = Planet.MERCURY;
        Planet actualPlanet = Planet.valueOfPlanet("MERCURY");

        Assert.assertEquals(expectedPlanet, actualPlanet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNoEnumValue() {
        Planet expectedPlanet = Planet.MERCURY;
        Planet actualPlanet = Planet.valueOf("EARTH");

        Assert.assertEquals(expectedPlanet, actualPlanet);
    }
}
