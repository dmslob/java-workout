package com.dmslob.collections.arrays;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@Slf4j
public class ArrayTest {

    @Test
    public void should_copy_references_when_item_is_array() {
        //given
        final String[][] src = {
                {"1", "2", "3", "4", "5"},
                {"6", "7", "8", "9", "10"}};
        //when
        String[][] dest = Arrays.copyOf(src, 3);
        src[1][1] = "4";
        //then
        assertThat(src[1]).isEqualTo(dest[1]);
    }

    @Test
    public void should_have_the_same_size_when_item_is_null() {
        //given
        final String[] cars = new String[]{"BMW", "Ford", "Lexus"};
        //when
        cars[0] = null;
        //then
        assertEquals(3, cars.length);
        assertThat(cars[0]).isNull();
    }

    @Test
    public void covarianceTest() {
        //given
        Integer[] integers = {1, 2, 3};
        Number[] numbers = integers; // valid
        //when
        Number firstElement = numbers[0]; // valid
        boolean isInteger = (firstElement instanceof Integer);
        //then
        assertThat(isInteger).isTrue();
        //when
        // Will produce ArrayStoreException
        // numbers[0] = 4L;
        // numbers[0] = 4.5F;
    }
}