package com.dmslob.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class WorkingDaysUtil {

    public String formatDateFromLong(String pattern, String dateStr) {
        return DateTimeFormatter.ofPattern(pattern)
                .format(Instant.ofEpochMilli(Long.valueOf(dateStr))
                        .atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public LocalDate getLastWorkingDayOfMonth(LocalDate lastDayOfMonth) {

        LocalDate lastWorkingDayOfMonth;
        switch (DayOfWeek.of(lastDayOfMonth.get(ChronoField.DAY_OF_WEEK))) {
            case SATURDAY:
                lastWorkingDayOfMonth = lastDayOfMonth.minusDays(1);
                break;
            case SUNDAY:
                lastWorkingDayOfMonth = lastDayOfMonth.minusDays(2);
                break;
            default:
                lastWorkingDayOfMonth = lastDayOfMonth;
        }
        return lastWorkingDayOfMonth;
    }

    public static LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }

    public static LocalDate subtractDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int subtractedDays = 0;
        while (subtractedDays < days) {
            result = result.minusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++subtractedDays;
            }
        }
        return result;
    }
}
