package com.dmslob.java17;

import org.testng.annotations.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DayPeriodSupportTest {

    @Test
    public void should_format_with_en_language() {
        // given
        var dtf = DateTimeFormatter.ofPattern("B")
                .withLocale(Locale.forLanguageTag("en"));
        // when | then
        var inTheMorning = dtf.format(LocalTime.of(8, 0));
        assertThat(inTheMorning).isEqualTo("in the morning");

        var inTheAfternoon = dtf.format(LocalTime.of(13, 0));
        assertThat(inTheAfternoon).isEqualTo("in the afternoon");

        var inTheEvening = dtf.format(LocalTime.of(20, 0));
        assertThat(inTheEvening).isEqualTo("in the evening");

        var midnight = dtf.format(LocalTime.of(0, 0));
        assertThat(midnight).isEqualTo("midnight");

        var atNight = dtf.format(LocalTime.of(1, 0));
        assertThat(atNight).isEqualTo("at night");
    }
}
