package com.dmslob.optional;

import org.testng.annotations.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testng.Assert.*;

public class OptionalTest {

    @Test
    public void should_throw_NPE_when_value_is_null() {
        // given
        String name = null;
        // when | then
        assertThatThrownBy(() -> Optional.of(name))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void should_not_be_present_when_value_is_null() {
        // given
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        // when | then
        assertFalse(opt.isPresent());
        assertTrue(opt.isEmpty());
    }

    @Test
    public void should_get_default_value_by_orElse_when_null() {
        // given
        String nullName = null;
        String defaultName = "Default Value";
        // when
        String name = Optional
                .ofNullable(nullName)
                .orElse(getDefault());
        // then
        assertEquals(name, defaultName);
    }

    @Test
    public void should_get_default_optional_when_value_is_null() {
        // given
        String nullName = null;
        String defaultName = "Default Value";
        // when
        var opt = Optional
                .ofNullable(nullName)
                .or(() -> Optional.of(defaultName));
        assertEquals(opt.get(), defaultName);
    }

    @Test
    public void should_call_getDefault_by_orElse_when_value_is_not_null() {
        // given
        String text = "Text present";
        // when | then
        System.out.println("Before orElseGet");
        String defaultText = Optional
                .ofNullable(text)
                .orElseGet(this::getDefault);
        assertEquals(defaultText, text);

        System.out.println("Before orElse");
        defaultText = Optional
                .ofNullable(text)
                .orElse(getDefault());
        assertEquals(defaultText, text);
    }

    String getDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }
}
