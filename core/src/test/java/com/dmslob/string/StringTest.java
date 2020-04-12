package com.dmslob.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class StringTest {
    private static final Logger LOGGER = LogManager.getLogger(StringTest.class);

    @Test
    public void stringJoinerTest() {
        StringJoiner sj = new StringJoiner(":", "[", "]");
        sj.add("George").add("Sally").add("Fred");
        String desiredString = sj.toString();

        Assert.assertEquals("[George:Sally:Fred]", desiredString);
    }

    @Test
    public void stringJoiningWithStreamTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        String commaSeparatedNumbers = numbers.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(", "));

        Assert.assertEquals("1, 2, 3, 4", commaSeparatedNumbers);
    }

    @Test
    public void removeFromStringByTextTest() {
        String formToRemove = "Pappas & Co, a California Corporation";
        String existedFormsString = "Pappas Family Farms, Inc.\nPappas & Co, a California Corporation\nPappas Family Farms I, LP";
        int startIndex = existedFormsString.indexOf(formToRemove);
        if (startIndex > 0) {
            StringBuilder builder = new StringBuilder(existedFormsString);
            builder.delete(startIndex, (startIndex + (formToRemove.length() + 1)));
            existedFormsString = builder.toString();
        }
        LOGGER.info(existedFormsString);
        //Assert.assertEquals("07-02-1478 07-02-1479 07-02-1481", existedFormsString);
    }

    @Test
    public void removeFromStringByTextByStringUtils() {
        String formToRemove = "Pappas & Co, a California Corporation";
        String existedFormsString = "Pappas Family Farms, Inc.\nPappas & Co, a California Corporation\nPappas Family Farms I, LP";
        String[] splited = existedFormsString.split("\n");

        LOGGER.info(Arrays.toString(splited));
    }
}
