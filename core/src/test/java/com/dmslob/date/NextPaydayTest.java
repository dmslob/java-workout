package com.dmslob.date;

import org.junit.Assert;
import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NextPaydayTest {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy MMM d");

    @Test
    public void nextPaydayTest() {
        String actualDateString = null;
        String expectedDateString = "2020 May 29";

        LocalDate today = LocalDate.of(2020, 5, 21);
        LocalDate nextPayday = today.with(new PaydayAdjuster());

        try {
            actualDateString = today.format(format);
            System.out.printf("Given the date:  %s%n", actualDateString);
            actualDateString = nextPayday.format(format);
            System.out.printf("The next payday: %s%n", actualDateString);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", actualDateString);
            throw exc;
        }

        Assert.assertEquals(expectedDateString, actualDateString);
    }
}
