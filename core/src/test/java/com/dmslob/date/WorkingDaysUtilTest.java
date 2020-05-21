package com.dmslob.date;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class WorkingDaysUtilTest {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private WorkingDaysUtil workingDaysUtil = new WorkingDaysUtil();
    private String dateTimestampString = "1587700800000";

    @Test
    public void lastWorkDayOfCurrentMonthTest() {
        LocalDate lastDayOfCurrentMonth = LocalDate.of(2020, 5, 21).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate lastWorkDayOfCurrentMonth = workingDaysUtil.getLastWorkingDayOfMonth(lastDayOfCurrentMonth);

        Assert.assertEquals(DayOfWeek.FRIDAY, lastWorkDayOfCurrentMonth.getDayOfWeek());

    }

    @Test
    public void formatDateTest() {
        String expectedDateString = "2020-04-24";
        String actualDateString = workingDaysUtil.formatDateFromLong(DATE_PATTERN, dateTimestampString);

        Assert.assertEquals(expectedDateString, actualDateString);
    }
}
