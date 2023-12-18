package com.dmslob.java17;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DayPeriodSupportTest {

    @Test
    public void should_format_with_local_language() {
        // given
        var dtf = DateTimeFormatter.ofPattern("B");
        // when | then
        System.out.println(dtf.format(LocalTime.of(8, 0)));
        System.out.println(dtf.format(LocalTime.of(13, 0)));
        System.out.println(dtf.format(LocalTime.of(20, 0)));
        System.out.println(dtf.format(LocalTime.of(23, 0)));
        System.out.println(dtf.format(LocalTime.of(0, 0)));

        System.out.println(DateTimeFormatter.ofPattern("G").format(LocalDate.now()));
        System.out.println(DateTimeFormatter.ofPattern("z").format(ZonedDateTime.now()));
        System.out.println(DateTimeFormatter.ofPattern("v").format(ZonedDateTime.now()));
    }

    @Test
    public void should_format_with_dutch_language() {
        // given
        var dtf = DateTimeFormatter.ofPattern("B")
                .withLocale(Locale.forLanguageTag("en"));
        // when | then
        System.out.println(dtf.format(LocalTime.of(8, 0)));
        System.out.println(dtf.format(LocalTime.of(13, 0)));
        System.out.println(dtf.format(LocalTime.of(20, 0)));
        System.out.println(dtf.format(LocalTime.of(0, 0)));
        System.out.println(dtf.format(LocalTime.of(1, 0)));
    }
}
