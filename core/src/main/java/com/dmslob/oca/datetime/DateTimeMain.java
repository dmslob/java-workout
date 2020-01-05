package com.dmslob.oca.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DateTimeMain {

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static void main(String[] args) {
        //init();
        //manipulating();

        LocalDate now = LocalDate.now();

        Date today = new Date();
        LocalDate localDate = convertToLocalDateViaInstant(today);
        System.out.println(localDate.toString());
        int year = localDate.getYear();
        System.out.println(year);
        LocalDate newLocalDate = null;
        if (year == 2019) {
            newLocalDate = localDate.plusYears(1);
        }
        Date newDate = convertToDateViaInstant(newLocalDate);
        System.out.println(newDate.toString());

        System.out.println(now.getDayOfMonth());

        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime = LocalDateTime.of(now, time);
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
        DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println(shortDateTime.format(dateTime)); // 1/20/20
        System.out.println(shortDateTime.format(date)); // 1/20/20
        //System.out.println(shortDateTime.format(time)); // UnsupportedTemporalTypeException

        //isoLocal();
        //format();

        LocalDateTime d = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
        Period p = Period.of(1, 2, 3);
        d = d.minus(p);
        System.out.println(d.toString());
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);//time 11:22 AM
        System.out.println(d.format(f));

        LocalDateTime d1 = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
        Period p1 = Period.ofDays(1).ofYears(2);
        d1 = d1.minus(p1);
        System.out.println(d1.toString());
        DateTimeFormatter f1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT); // dateTime 5/10/13 11:22 AM
        System.out.println(f1.format(d1));
        System.out.println(d1.format(f1));

        //LocalDate.of(2018, Month.APRIL, 40); // A runtime exception is thrown.

        t2();
    }

    static void t1() {
        LocalDate date = LocalDate.of(2018, Month.APRIL, 30);
        date.plusDays(2);
        date.plusYears(3);
        System.out.println(date.getYear() + " " + date.getMonth() + " " + date.getDayOfMonth());
    }

    static void t2() {
        LocalDateTime d = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
        Period p = Period.of(1, 2, 3);
        d = d.minus(p);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(d.format(f));
    }

    static void format() {
        LocalDateTime d = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
        System.out.println(d.toString());
        Period p = Period.of(1, 2, 3);
        d = d.minus(p);
        System.out.println(d.toString());
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(d.format(f));
        System.out.println(f.format(d));

        LocalDateTime dd = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
        Period pp = Period.ofDays(1).ofYears(2);
        dd = dd.minus(pp);
        DateTimeFormatter ff = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println(ff.format(dd));
    }

    static void isoLocal() {
        LocalDate date = LocalDate.parse("2018-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
        //date.plusDays(2);
        //date.plusHours(3); compile error
        System.out.println(date.toString());
        System.out.println(date.getYear() + " " + date.getMonth() + " " + date.getDayOfMonth());

        //LocalDate date1 = LocalDate.of(2018, Month.APRIL, 40); // DateTimeException
        LocalDate date1 = LocalDate.of(2018, Month.MAY, 31);
        date1.plusDays(5);
        System.out.println(date1.toString());
    }

    static void manipulating() {
        LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
        System.out.println(date); // 2014-01-20

        date = date.plusDays(2);
        System.out.println(date); // 2014-01-22

        date = date.plusWeeks(1);
        System.out.println(date); // 2014-01-29

        date = date.plusMonths(1);
        System.out.println(date); // 2014-02-28

        date = date.plusYears(5);
        System.out.println(date); // 2019-02-28

        LocalDate newdate = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(5, 15);
        LocalDateTime dateTime = LocalDateTime.of(newdate, time);
        System.out.println(dateTime); // 2020-01-20T05:15

        Period wrong = Period.ofYears(1).ofWeeks(1); // every week
    }

    static void init() {
        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2015, 1, 20);

        System.out.println(date1.toString());
        System.out.println(date2.toString());

        LocalTime time1 = LocalTime.of(6, 15); // hour and minute
        LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds

        LocalDate.of(2015, Month.JANUARY, 32); // throws DateTimeException
    }
}
