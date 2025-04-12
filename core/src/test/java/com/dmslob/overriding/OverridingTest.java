package com.dmslob.overriding;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OverridingTest {
    @Test
    public void should_use_overridden_method() {
        // given
        double expected = 0.1d;
        // when
        double result = add(10.0d, null);
        // then
        assertThat(result).isEqualTo(expected);
    }

    double add(double d1, double d2) {
        return 0.0;
    }

    double add(Double d1, Double d2) {
        return 0.1;
    }
}
