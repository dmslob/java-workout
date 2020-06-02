package com.dmslob.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;

public class MethodReferenceTest {

    @Test
    public void referenceTest() throws Exception {
        Car sportCar = new SportCar();

        Callable<String> driveReference = sportCar::drive;
        sportCar = new Truck();
        String actual = driveReference.call();

        Assert.assertEquals("Sport Car", actual);
    }
}
