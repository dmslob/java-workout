package com.dmslob.collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.*;
import java.util.function.Consumer;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IterationTest {

    @Test
    public void should_set_item() {
        // given
        var letters = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Consumer<String> setElement = letter -> {
            if (letter != null && letter.equals("D")) {
                letters.set(3, "G");
            }
        };
        // when
        letters.forEach(setElement);
        // then
        assertThat(letters).contains("A", "B", "C", "G");
    }

    @Test
    public void should_throw_exception_when_add_or_remove_item() {
        // given
        var letters = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Consumer<String> addElement = letter -> {
            if (letter != null && letter.equals("A")) {
                letters.add("D");
            }
        };
        Consumer<String> removeElement = letter -> {
            if (letter != null && letter.equals("A")) {
                letters.remove("D");
            }
        };
        // when | then
        assertThatThrownBy(() -> letters.forEach(addElement))
                .isInstanceOf(ConcurrentModificationException.class);

        assertThatThrownBy(() -> letters.forEach(removeElement))
                .isInstanceOf(ConcurrentModificationException.class);
    }
}
