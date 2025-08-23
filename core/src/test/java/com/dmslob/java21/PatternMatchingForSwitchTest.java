package com.dmslob.java21;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PatternMatchingForSwitchTest {

    @Test
    public void should_test_instanceof_prior_java_16() {
        var obj = "Hello World";
        // Prior to Java 16
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.println(s);
        }
    }

    @Test
    public void should_test_instanceof_java_16() {
        var obj = "Hello World";
        // As of Java 16
        if (obj instanceof String s) {
            System.out.println(s);
        }
    }

    // Prior to Java 21
    String formatter(Object obj) {
        String formatted = "unknown";
        if (obj instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (obj instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (obj instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (obj instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    @Test
    public void should_test_instanceof_prior_java_21() {
        // given
        var obj = "Hello World";
        // when
        var result = formatter(obj);
        // then
        assertThat(result).isEqualTo("String Hello World");

        // given
        var i = 100;
        // when
        result = formatter(i);
        // then
        assertThat(result).isEqualTo("int 100");
    }

    // As of Java 21
    String formatterPatternSwitch(Object obj) {
        return switch (obj) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> obj.toString();
        };
    }

    @Test
    public void should_test_instanceof_as_java_21() {
        // given
        var obj = "Hello World";
        // when
        var result = formatterPatternSwitch(obj);
        // then
        assertThat(result).isEqualTo("String Hello World");

        // given
        var i = 100;
        // when
        result = formatterPatternSwitch(i);
        // then
        assertThat(result).isEqualTo("int 100");
    }

    // Switches and null
    // Prior to Java 21
    void testFooBarOld(String s) {
        if (s == null) {
            System.out.println("Oops!");
            return;
        }
        switch (s) {
            case "Foo", "Bar" -> System.out.println("Great");
            default -> System.out.println("Ok");
        }
    }

    // As of Java 21
    void testFooBarNew(String s) {
        switch (s) {
            case null -> System.out.println("Oops");
            case "Foo", "Bar" -> System.out.println("Great");
            default -> System.out.println("Ok");
        }
    }

    @Test
    public void should_test_switch_with_null() {
        // when
        testFooBarOld(null);
        testFooBarNew(null);
    }

    // Case refinement
    // As of Java 21
    void testStringOld(String response) {
        switch (response) {
            case null -> System.out.println("Oops");
            case String s -> {
                if (s.equalsIgnoreCase("YES"))
                    System.out.println("You got it");
                else if (s.equalsIgnoreCase("NO"))
                    System.out.println("Shame");
                else
                    System.out.println("Sorry?");
            }
        }
    }

    // As of Java 21
    void testStringNew(String response) {
        switch (response) {
            case null -> System.out.println("Oops");
            case String s
                    when s.equalsIgnoreCase("YES") -> {
                System.out.println("You got it");
            }
            case String s
                    when s.equalsIgnoreCase("NO") -> {
                System.out.println("Shame");
            }
            case String s -> System.out.println("Sorry?");
        }
    }

    @Test
    public void should_test_case_refinement() {
        // given
        String ward = "Mmm";
        // when
        testStringNew(ward);
    }

    // As of Java 21
    static void testStringEnhanced(String response) {
        switch (response) {
            case null -> System.out.println("null");
            case "y", "Y" -> {
                System.out.println("You got it");
            }
            case "n", "N" -> {
                System.out.println("Shame");
            }
            case String s
                    when s.equalsIgnoreCase("YES") -> {
                        System.out.println("You got it");
                    }
            case String s
                    when s.equalsIgnoreCase("NO") -> {
                        System.out.println("Shame");
                    }
            case String s -> System.out.println("Sorry?");
        }
    }

    // Switches and enum constants
    // Prior to Java 21
    public enum CardSuit { CLUBS, DIAMONDS, HEARTS, SPADES }

    void testForHearts(CardSuit s) {
        switch (s) {
            case HEARTS -> System.out.println("It's a heart!");
            case DIAMONDS -> System.out.println("It's a diamond!");
            default -> System.out.println("Some other suit");
        }
    }

    // As of Java 21
    sealed interface CardClassification permits Suit, Tarot {}
    public enum Suit implements CardClassification { CLUBS, DIAMONDS, HEARTS, SPADES }
    final class Tarot implements CardClassification {}

    void exhaustiveSwitchWithBetterEnumSupport(CardClassification c) {
        switch (c) {
            case Suit.CLUBS -> {
                System.out.println("It's clubs");
            }
            case Suit.DIAMONDS -> {
                System.out.println("It's diamonds");
            }
            case Suit.HEARTS -> {
                System.out.println("It's hearts");
            }
            case Suit.SPADES -> {
                System.out.println("It's spades");
            }
            case Tarot t -> {
                System.out.println("It's a tarot");
            }
        }
    }

    // As of Java 21
    record Point(int i, int j) {}
    enum Color { RED, GREEN, BLUE; }

    void typeTester(Object obj) {
        switch (obj) {
            case null     -> System.out.println("null");
            case String s -> System.out.println("String");
            case Color c  -> System.out.println("Color: " + c);
            case Point p  -> System.out.println("Record class: " + p);
            case int[] ia -> System.out.println("Array of ints with length " + ia.length);
            default       -> System.out.println("Something else");
        }
    }

    @Test
    public void should_test() {
        // when
        typeTester(new int[] {1, 3});

        Integer i = 1;
        switch (i) {
            case -1, 1 -> System.out.println("Special case");
            case Integer j when j > 0 -> System.out.println("Positive integer cases");
            case Integer j -> System.out.println("All the remaining integers");
            //case Double d -> System.out.println("error");
        }
    }

    // As of Java 21
    static void first(Object obj) {
        switch (obj) {
            case String s ->
                    System.out.println("A string: " + s);
            case CharSequence cs ->
                    System.out.println("A sequence of length " + cs.length());
            default -> {
                break;
            }
        }
    }

//    void error(Object obj) {
//        switch (obj) {
//            case CharSequence cs ->
//                    System.out.println("A sequence of length " + cs.length());
//            case String s ->    // Error - pattern is dominated by previous pattern
//                    System.out.println("A string: " + s);
//            default -> {
//                break;
//            }
//        }
//    }
}
