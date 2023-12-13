package com.dmslob.java17;

import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class CompactNumberFormatTest {

    @Test
    public void should_test_number_format() {
        // given
        var fmt = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.SHORT);
        //when | then
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));

        // given
        fmt = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.LONG);
        //when | then
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));

        // given
        fmt = NumberFormat.getCompactNumberInstance(Locale.forLanguageTag("NL"), NumberFormat.Style.LONG);
        //when | then
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));
    }
}
