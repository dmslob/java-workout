package com.dmslob.optional;

import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testng.Assert.*;

public class OptionalTest {
    @Test
    public void should_be_empty_when_empty_optional() {
        // given
        Optional<String> empty = Optional.empty();
        // when | then
        assertFalse(empty.isPresent());
        assertTrue(empty.isEmpty());
    }

    @Test
    public void should_present_value() {
        // given
        String name = "test name";
        Optional<String> opt = Optional.of(name);
        // when | then
        assertTrue(opt.isPresent());
    }

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
    }

    @Test
    public void should_present_when_not_null() {
        // given
        String name = "test name";
        Optional<String> opt = Optional.ofNullable(name);
        // when | then
        assertTrue(opt.isPresent());
    }

    @Test
    public void should_check_value_presence() {
        // given
        Optional<String> opt = Optional.of("value");
        // when | then
        assertFalse(opt.isEmpty());

        opt = Optional.ofNullable(null);
        assertTrue(opt.isEmpty());
    }

    @Test
    public void should_complete_action_if_present() {
        // given
        Optional<String> opt = Optional.of("value");
        // when | then
        opt.ifPresent(value -> System.out.println(value.length()));
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
    public void foo() {
        float a = 35 / 0;
    }

    @Test
    public void should_get_default_value_by_orElseGet_when_null() {
        // given
        String nullName = null;
        String defaultName = "Default Value";
        // when
        String name = Optional
                .ofNullable(nullName)
                .orElseGet(this::getDefault);
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

        Optional
                .ofNullable(nullName)
                .ifPresentOrElse(value -> System.out.println("Present"),
                        () -> System.out.println("Not present"));
    }

    @Test
    public void should_call_getDefault_by_orElse_when_not_null() {
        // given
        String text = "Text present";
        // when | then
        System.out.println("Using orElseGet:");
        String defaultText = Optional
                .ofNullable(text)
                .orElseGet(this::getDefault);
        assertEquals(defaultText, text);

        System.out.println("Using orElse:");
        defaultText = Optional
                .ofNullable(text)
                .orElse(getDefault());
        assertEquals(defaultText, text);
    }

    private String getDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    @Test
    public void should_throw_exception_by_orElseThrow_when_null_value() {
        // given
        String nullName = null;
        // when | then
        assertThatThrownBy(() -> Optional
                .ofNullable(nullName)
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_throw_NoSuchElementException_when_no_exception_as_param() {
        // given
        String nullName = null;
        // when | then
        assertThatThrownBy(() -> Optional
                .ofNullable(nullName)
                .orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void should_get_value_from_optional() {
        // given
        Optional<String> stringOptional = Optional.of("value");
        // when | then
        String name = stringOptional.get();
        assertEquals(name, "value");
    }

    @Test
    public void should_throw_exception_when_value_is_null() {
        // given
        var stringOptional = Optional.ofNullable(null);
        // when | then
        assertThatThrownBy(() -> stringOptional.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void should_check_password() {
        // given
        String password = " password ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt
                .filter(pass -> pass.equals(password))
                .isPresent();
        assertFalse(correctPassword);

        correctPassword = passOpt
                .map(String::trim)
                .filter(pass -> pass.equals(password))
                .isPresent();
        assertTrue(correctPassword);
    }
}
