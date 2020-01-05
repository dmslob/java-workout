package com.dmslob.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Main {

    public static void main(String[] args) {
        //testTemporalAdjustersBase();
        String DATE_FORMATTER = "M/d/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String effDate = "4/11/19";
        LocalDate localDate = LocalDate.parse(effDate, formatter);
        System.out.println(localDate.toString());

        String anotherEffDate = "04/29/19";
        LocalDate localDate2 = LocalDate.parse(anotherEffDate, formatter);
        System.out.println(localDate2.toString());
        boolean after = localDate2.isAfter(localDate);
        System.out.println(after);
    }

    public static void testTemporalAdjustersBase() {
        LocalDate date = LocalDate.of(2019, Month.FEBRUARY, 28);
        DayOfWeek dotw = date.getDayOfWeek();

        System.out.printf("%s is on a %s%n", date, dotw);

        System.out.printf("first day of Month: %s%n",
                date.with(TemporalAdjusters.firstDayOfMonth()));

        System.out.printf("first Monday of Month: %s%n",
                date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));

        System.out.printf("last day of Month: %s%n",
                date.with(TemporalAdjusters.lastDayOfMonth()));

        System.out.printf("first day of next Month: %s%n",
                date.with(TemporalAdjusters.firstDayOfNextMonth()));

        System.out.printf("first day of next Year: %s%n",
                date.with(TemporalAdjusters.firstDayOfNextYear()));

        System.out.printf("first day of Year: %s%n",
                date.with(TemporalAdjusters.firstDayOfYear()));
    }
}