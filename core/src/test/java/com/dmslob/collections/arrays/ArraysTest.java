package com.dmslob.collections.arrays;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class ArraysTest {

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

    @Test
    public void should_concatenate_arrays() {
        // given
        Integer[] intsA = {1, 2, 3};
        Integer[] intsB = {3, 4, 5};
        Integer[] expected = {1, 2, 3, 3, 4, 5};
        // when
        Integer[] actualResult = ArraysUtil.concat(intsA, intsB);
        // then
        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void should_reverse_array() {
        // given
        int[] ints = {1, 2, 3, 4, 5};
        int[] expected = {5, 4, 3, 2, 1};
        // when
        int[] actualResult = ArraysUtil.reverse(ints);
        // then
        assertThat(actualResult).isEqualTo(expected);
    }
}