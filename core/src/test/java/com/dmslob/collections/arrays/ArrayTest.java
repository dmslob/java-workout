package com.dmslob.collections.arrays;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class ArrayTest {

    @Test
    public void should_copy_references_when_item_is_array() {
        //given
        final String[][] src = {
                {"1", "2", "3", "4", "5"},
                {"6", "7", "8", "9", "10"}
        };
        //when
        String[][] dest = Arrays.copyOf(src, 3);
        src[1][1] = "4";
        //then
        assertThat(src[1]).isEqualTo(dest[1]);
    }

    @Test
    public void covariance_test() {
        //given
        Integer[] integers = {1, 2, 3};
        Number[] numbers = integers; // valid
        //when
        Number firstElement = numbers[0]; // valid
        boolean isInteger = (firstElement instanceof Integer);
        //then
        assertThat(isInteger).isTrue();
        //when
        assertThatThrownBy(() -> numbers[0] = 4L)
                .isInstanceOf(ArrayStoreException.class);
        assertThatThrownBy(() -> numbers[0] = 4.5f)
                .isInstanceOf(ArrayStoreException.class);
    }
}