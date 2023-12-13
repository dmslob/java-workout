package com.dmslob;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstanceofTest {

    @Test
    public void should_be_true_when_instanceof_string() {
        // given
        String nullableString = "any string";
        // when
        var isString = nullableString instanceof String;
        // then
        assertThat(isString).isTrue();
    }

    @Test
    public void should_not_be_true_when_instanceof_nullableString() {
        // given
        String nullableString = null;
        // when
        var isString = nullableString instanceof String;
        // then
        assertThat(isString).isFalse();
    }

    @Test
    public void should_compile_when_instanceof_with_inconvertible_types() {
        // given
        AnyType anyType = new AnyType();
        // when | then
        // compile error: can not cast. Inconvertible types.
        // var isString = anyType instanceof String;
    }

    private static class AnyType {
    }
}
