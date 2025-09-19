package com.dmslob.java21;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.StringTemplate.RAW;
import static java.util.FormatProcessor.FMT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StringTemplatesTest {

    @Test
    public void should_format_string_by_formatted() {
        // given
        Gson gson = new Gson();
        record Person(String name, String phone, String address) {
        }

        String name = "Joan Smith";
        String phone = "555-123-4567";
        String address = "1 Maple Drive, London";
        var jaanSmith = new Person(name, phone, address);

        String json = """
                {
                    "name":    "%s",
                    "phone":   "%s",
                    "address": "%s"
                }
                """.formatted(name, phone, address);// Java 15
        // when
        var person = gson.fromJson(json, Person.class);
        // then
        assertThat(person).isEqualTo(jaanSmith);
        // given
        json = STR."""
                {
                    "name":    "\{name}",
                    "phone":   "\{phone}",
                    "address": "\{address}"
                }
                """;
        // when
        person = gson.fromJson(json, Person.class);
        // then
        assertThat(person).isEqualTo(jaanSmith);
        // given
        StringTemplate template = RAW."""
                {
                    "name":    "\{name}",
                    "phone":   "\{phone}",
                    "address": "\{address}"
                }
                """;
		System.out.println(template.fragments());
		System.out.println(template.values());
		System.out.println(template.interpolate());
		// when
        json = STR.process(template);
		System.out.println(json);
        person = gson.fromJson(json, Person.class);
        // then
        assertThat(person).isEqualTo(jaanSmith);
    }

    @Test
    public void should_format_string_by_StringTemplate() {
        // given
        int x = 10, y = 20;
        // when
        String stringExpression = STR."\{x} + \{y} = \{x + y}";
        // then
        assertThat(stringExpression).isEqualTo("10 + 20 = 30");

        // when
        String time = STR."The time is \{
                DateTimeFormatter
                        .ofPattern("HH:mm:ss")
                        .format(LocalTime.of(12, 12, 12))
                } right now";
        // then
        assertThat(time).isEqualTo("The time is 12:12:12 right now");

        // There is no limit to the number of embedded expressions in a string template expression.
        // The embedded expressions are evaluated from left to right,
        // just like the arguments in a method invocation expression.
        // given
        int index = 0;
        // when
        String data = STR."\{index++}, \{index++}, \{index++}, \{index++}";
        // then
        assertThat(data).isEqualTo("0, 1, 2, 3");
    }

    @Test
    public void should_format_string_by_FormatProcessor() {
        // given
        record Rectangle(String name, double width, double height) {
            double area() {
                return width * height;
            }
        }
        Rectangle[] zone = new Rectangle[]{
                new Rectangle("Alfa", 17.8, 31.4),
                new Rectangle("Bravo", 9.6, 12.4),
                new Rectangle("Charlie", 7.1, 11.23),
        };
        String expected = """
                Description     Width    Height     Area
                Alfa            17.80    31.40      558.92
                Bravo            9.60    12.40      119.04
                Charlie          7.10    11.23       79.73
                                             Total  757.69
                """;
        // when
        String table = FMT."""
            Description     Width    Height     Area
            %-12s\{zone[0].name}  %7.2f\{zone[0].width}  %7.2f\{zone[0].height}     %7.2f\{zone[0].area()}
            %-12s\{zone[1].name}  %7.2f\{zone[1].width}  %7.2f\{zone[1].height}     %7.2f\{zone[1].area()}
            %-12s\{zone[2].name}  %7.2f\{zone[2].width}  %7.2f\{zone[2].height}     %7.2f\{zone[2].area()}
            \{" ".repeat(28)} Total %7.2f\{zone[0].area() + zone[1].area() + zone[2].area()}
            """;
        // then
        assertThat(table).isEqualTo(expected);
		System.out.println(table);
	}
}
