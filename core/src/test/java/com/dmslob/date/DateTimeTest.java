package com.dmslob.date;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateTimeTest {

    @Test
    public void shouldCalculateYears() {
        LocalDate someSpecificDate = LocalDate.of(2020, Month.SEPTEMBER, 18);

        SpiderMan spiderMan = new SpiderMan("The Spider Man",
                LocalDate.of(1962, Month.SEPTEMBER, 8));

        Period period = Period.between(spiderMan.getCreationDate(), someSpecificDate);

        final long actualYears = period.get(ChronoUnit.YEARS);
        final long expectedYears = 56;

        Assert.assertEquals(expectedYears, actualYears);
    }

    @Test
    public void shouldCalculateDays() {
        LocalDate someSpecificDate = LocalDate.of(2020, Month.SEPTEMBER, 18);

        SpiderMan spiderMan = new SpiderMan("The Spider Man",
                LocalDate.of(1962, Month.SEPTEMBER, 8));

        Period period = Period.between(spiderMan.getCreationDate(), someSpecificDate);

        final long actualDays = period.get(ChronoUnit.DAYS);
        final long expectedDays = 8;

        Assert.assertEquals(expectedDays, actualDays);
    }

    @Test
    public void shouldSubtractDays() {
        LocalDateTime someSpecificDate = LocalDateTime
                .of(2020, 5, 21, 0, 0)
                .minusMonths(4);

        int expectedMonthNumber = 1;
        Assert.assertEquals(expectedMonthNumber, someSpecificDate.getMonthValue());

        int expectedHour = 0;
        Assert.assertEquals(expectedHour, someSpecificDate.getHour());
    }
}
