package com.dmslob.date;

import org.apache.commons.text.WordUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DateTimeTest {

    @Test
    public void testYears() {
        String fName = "creditCardsCreationReqId";
        String[] r = fName.split("(?=\\p{Upper})");
        String f = Arrays.stream(r).map(s -> s.toUpperCase()).collect(Collectors.joining("_"));
        System.out.println(f);

        String bFalse = Objects.toString(false);
        System.out.println("b: " + bFalse);
        LocalDate now = LocalDate.of(2017, Month.SEPTEMBER, 18);
        SpiderMan spiderMan = new SpiderMan("The Spider Man",
                LocalDate.of(1962, Month.SEPTEMBER, 8));
        Period period = Period.between(spiderMan.getCreationDate(), now);

        final long actualYears = period.get(ChronoUnit.YEARS);
        final long expectedYears = 53;

        Assert.assertEquals(expectedYears, actualYears);
    }

    @Test
    public void testDays() {
        LocalDate now = LocalDate.of(2017, Month.SEPTEMBER, 18);
        SpiderMan spiderMan = new SpiderMan(
                "The Spider Man",
                LocalDate.of(1962,
                        Month.SEPTEMBER, 8));

        Period period = Period.between(spiderMan.getCreationDate(), now);

        final long actualDays = period.get(ChronoUnit.DAYS);
        final long expectedDays = 8;

        Assert.assertEquals(expectedDays, actualDays);
    }

    @Test
    public void minusDaysTest() {
        LocalDateTime ldt = LocalDateTime
                .of(2020, 5, 21, 0, 0)
                .minusMonths(4);

        System.out.println(ldt.toString());

        int expectedMonthNumber = 1;
        Assert.assertEquals(expectedMonthNumber, ldt.getMonthValue());

        int expectedHour = 0;
        Assert.assertEquals(expectedHour, ldt.getHour());

        System.out.println(ldt.get(ChronoField.HOUR_OF_DAY));
    }
}
