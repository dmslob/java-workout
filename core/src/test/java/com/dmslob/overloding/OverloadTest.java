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

    private static class ConfusingType {
        public String getType(Object o) {
            return "Object";
        }

        public String getType(double[] doubles) {
            return "double array";
        }
    }
}
