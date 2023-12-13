package com.dmslob.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StringTest {

    @Test
    public void should_find_the_longest_word() {
        //given
        var sentence = "Today is the happiest day of my life";
        var expectedWord = "happiest";

        //when
        var longestWord = Arrays
                .stream(sentence.split(" "))
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        //then
        assertThat(longestWord).isEqualTo(expectedWord);
    }

    @Test
    public void should_join_strings_by_StringJoiner() {
        //given
        var expectedString = "[George:Sally:Fred]";
        var sj = new StringJoiner(":", "[", "]");

        //when
        sj.add("George").add("Sally").add("Fred");
        var actualString = sj.toString();

        //then
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_join_strings_by_stream() {
        //given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        //when
        var commaSeparatedNumbers = numbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        //then
        assertThat(commaSeparatedNumbers).isEqualTo("1, 2, 3, 4");
    }

    @Test
    public void should_count_characters() {
        //given
        String[] chars = {"a", "b", "a"};
        //when
        long numberOfChars = StringUtils.countCharacter(chars, "a");
        //then
        assertThat(numberOfChars).isEqualTo(2);
    }

    @Test
    public void splitStringOnArrayTest() {
        var str = "Hello how are you?";
        String[] strings = StringUtils.splitStringOnArray(str);

        assertThat(strings).hasSize(4);
    }

    @Test
    public void should_find_by_regex() {
        //given
        var text = "It was cool. But not ideally. It was perfect.";
        var regex = "[^.]*(cool|perfect)[^.]*(\\.|$)";
        //when
        String[] actualResult = StringUtils.findByRegex(text, regex);
        //then
        assertThat(actualResult).hasSize(2);
    }

    @Test
    public void shouldDivideByUppercase() {
        var stringToDivide = "creditCardsCreationReqId";
        String[] words = stringToDivide.split("(?=\\p{Upper})");

        var joinedResult = Arrays.stream(words)
                .map(String::toUpperCase)
                .collect(Collectors.joining("_"));

        var expectedResult = "CREDIT_CARDS_CREATION_REQ_ID";
        Assert.assertEquals(expectedResult, joinedResult);
    }

    @Test
    public void should_compare_strings() {
        // given
        var one = "abcd";
        var two = "abc";
        var three = one.substring(0, one.length());
        var four = one.substring(0, one.length() - 1);
        // when | then
        assertThat(one == three).isTrue();
        assertThat(two == four).isFalse();
    }

    @Test
    public void should_have_strings_with_the_same_hashcode() {
        // given
        String Aa = "Aa";
        String BB = "BB";
        String DB = "DB";
        String Ca = "Ca";
        String common_prefixDB = "common_prefixDB";
        String common_prefixCa = "common_prefixCa";

        // when | then
        assertThat(Aa.hashCode()).isEqualTo(BB.hashCode());
        assertThat(DB.hashCode()).isEqualTo(Ca.hashCode());
        assertThat(common_prefixDB.hashCode()).isEqualTo(common_prefixCa.hashCode());

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put(common_prefixDB, "1");
        stringMap.put(common_prefixCa, "2");
        System.out.println(stringMap.get(common_prefixDB));
    }
}
