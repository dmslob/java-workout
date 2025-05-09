package com.dmslob;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncrementTest {

    @Test
    public void should_increment() {
        // given
        int expected = 13;
        int i = 10;
        // when
        i++; i++; ++i;
        int j = i++;
        // then
        assertThat(j).isEqualTo(expected);
    }
}