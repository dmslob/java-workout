package com.dmslob.date;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class NextPayday {

    public static void main(String[] args) {

        Month month;
        LocalDate date;
        DateTimeFormatter format;
        String out = null;

        if (args.length < 2) {
            System.out.printf("Usage: NextPayday <month> <day>%n");
            throw new IllegalArgumentException();
        }

        try {
            month = Month.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException exc) {
            System.out.printf("%s is not a valid month.%n", args[0]);
            throw exc;   // Rethrow the exception.
        }

        int day = Integer.parseInt(args[1]);

        try {
            date = Year.now().atMonth(month).atDay(day);
        } catch (DateTimeException exc) {
            System.out.printf("%s %s is not a valid date.%n", month, day);
            throw exc;   // Rethrow the exception.
        }

        LocalDate nextPayday = date.with(new PaydayAdjuster());

        try {
            format = DateTimeFormatter.ofPattern("yyyy MMM d");
            out = date.format(format);
            System.out.printf("Given the date:  %s%n", out);
            out = nextPayday.format(format);
            System.out.printf("the next payday: %s%n", out);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", out);
            throw exc;
        }
    }
}
