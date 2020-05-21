package com.dmslob.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class PaydayAdjuster implements TemporalAdjuster {
    /**
     * The adjustInto method accepts a Temporal instance
     * and returns an adjusted LocalDate. If the passed in
     * parameter is not a LocalDate, then a DateTimeException is thrown.
     */
    public Temporal adjustInto(Temporal input) {

        LocalDate localDate = LocalDate.from(input);
        int day;

        if (localDate.getDayOfMonth() < 15) {
            day = 15;
        } else {
            day = localDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        }

        localDate = localDate.withDayOfMonth(day);
        if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY
                || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            localDate = localDate.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }

        return input.with(localDate);
    }
}
