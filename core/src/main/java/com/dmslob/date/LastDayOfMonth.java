package com.dmslob.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

public class LastDayOfMonth {

    public static void main(String args[]) {

        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Last day of the current month: "
                + lastDayofCurrentMonth.getDayOfWeek() + ", " + lastDayofCurrentMonth);

        LocalDate lastWorkDayCurrentMonth = new LastDayOfMonth().getLastWorkingDayOfMonth(lastDayofCurrentMonth);
        System.out.println("Last working day of current month: "
                + lastWorkDayCurrentMonth.getDayOfWeek() + ", " + lastWorkDayCurrentMonth);

        LocalDate lastDayofMonthGivenDate = LocalDate.of(2019, 03, 20).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("2A. Last day of month for '2019-03-20': "
                + lastDayofMonthGivenDate.getDayOfWeek() + ", " + lastDayofMonthGivenDate);

        LocalDate lastWorkDayGivenDate = new LastDayOfMonth().getLastWorkingDayOfMonth(lastDayofMonthGivenDate);
        System.out.println("2B. Last working day of month for '2019-03-20': "
                + lastWorkDayGivenDate.getDayOfWeek() + ", " + lastWorkDayGivenDate);

        LocalDate lastDayofMonthYear = YearMonth.of(2019, 04).atEndOfMonth();
        System.out.println("3A. Last day of month for 'Apr, 2019': "
                + lastDayofMonthYear.getDayOfWeek() + ", " + lastDayofMonthYear);

        LocalDate lastWorkDayMonthYear = new LastDayOfMonth().getLastWorkingDayOfMonth(lastDayofMonthYear);
        System.out.println("3B. Last working day of month for 'Apr, 2019': "
                + lastWorkDayMonthYear.getDayOfWeek() + ", " + lastWorkDayMonthYear);
    }

    private boolean isTodayIsLastWorkingDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastDayCurrentMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate lastWorkDayCurrentMonth = getLastWorkingDayOfMonth(lastDayCurrentMonth);

        return today.equals(lastWorkDayCurrentMonth);
    }

    private LocalDate getLastWorkingDayOfMonth(LocalDate lastDayOfMonth) {

        LocalDate lastWorkingDayofMonth;
        switch (DayOfWeek.of(lastDayOfMonth.get(ChronoField.DAY_OF_WEEK))) {
            case SATURDAY:
                lastWorkingDayofMonth = lastDayOfMonth.minusDays(1);
                break;
            case SUNDAY:
                lastWorkingDayofMonth = lastDayOfMonth.minusDays(2);
                break;
            default:
                lastWorkingDayofMonth = lastDayOfMonth;
        }
        return lastWorkingDayofMonth;
    }
}
