package com.dmslob.string;

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
    public void shouldJoinStringsByStringJoiner() {
        StringJoiner sj = new StringJoiner(":", "[", "]");
        sj.add("George").add("Sally").add("Fred");
        String actualString = sj.toString();

        Assert.assertEquals("[George:Sally:Fred]", actualString);
    }

    @Test
    public void shouldJoinStringsByStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        String commaSeparatedNumbers = numbers.stream()
                .map(number -> number.toString())
                .collect(Collectors.joining(", "));

        Assert.assertEquals("1, 2, 3, 4", commaSeparatedNumbers);
    }

    @Test
    public void stringHasOnlyWhiteSpacesTest() {
        String strOne = "String to Test";
        String strTwo = "              ";

        LOGGER.info("strTwo.trim(): [" + strTwo.trim() + "]");

        LOGGER.info("Is string [" + strOne + "] only whitespaces? "
                + StringUtils.isStringAllWhiteSpace(strOne));

        LOGGER.info("Is string [" + strTwo + "] only whitespaces? "
                + StringUtils.isStringAllWhiteSpace(strTwo));
    }

    @Test
    public void countCharacterTest() {
        String[] chars = {"a", "b", "a"};
        long numberOfChars = StringUtils.countCharacter(chars, "a");

        Assert.assertTrue(numberOfChars == 2);
    }

    @Test
    public void splitStringOnArrayTest() {
        String str = "Hello Dmytro how are you?";
        String[] strings = StringUtils.splitStringOnArray(str);

        Assert.assertTrue(strings.length == 5);
    }

    @Test
    public void shouldFindByRegex() {
        String text = "It was cool. But not ideally. It was perfect.";
        String regex = "[^.]*(cool|perfect)[^.]*(\\.|$)";

        String[] foundByRegex = StringUtils.findByRegex(text, regex);

        Assert.assertTrue(foundByRegex.length == 2);
    }

    @Test
    public void shouldDivideByUppercase() {
        String stringToDivide = "creditCardsCreationReqId";
        String[] words = stringToDivide.split("(?=\\p{Upper})");

        String joinedResult = Arrays.stream(words)
                .map(String::toUpperCase)
                .collect(Collectors.joining("_"));

        String expectedResult = "CREDIT_CARDS_CREATION_REQ_ID";
        Assert.assertEquals(expectedResult, joinedResult);
    }
}
