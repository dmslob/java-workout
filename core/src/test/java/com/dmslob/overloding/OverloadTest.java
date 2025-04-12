package com.dmslob.overloding;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OverloadTest {

    @Test
    public void should_select_array_when_null() {
        // given
        var expectedResult = "double array";
        var type = new ConfusingType();
        // when
        var result = type.getType(null);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static class ConfusingType {
        String getType(Object o) {
            return "Object";
        }

        String getType(double[] doubles) {
            return "double array";
        }
    }
}
