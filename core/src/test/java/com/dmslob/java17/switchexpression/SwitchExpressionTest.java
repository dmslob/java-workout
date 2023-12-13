package com.dmslob.java17.switchexpression;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class SwitchExpressionTest {

    @Test
    public void should_test_switch_expression() {
        // given
        var fruitType = FruitType.APPLE;
        var expected = "Common fruit";
        // when
        var result = getFrom(fruitType);
        // then
        assertThat(result).isEqualTo(expected);
    }

    private String getFrom(FruitType fruitType) {
        return switch (fruitType) {
            case APPLE, PEAR -> {
                log.info("Given fruit is {}", fruitType);
                yield "Common fruit";
            }
            case ORANGE, AVOCADO -> {
                log.info("Given fruit is {}", fruitType);
                yield "Exotic fruit";
            }
            default -> "Undefined fruit";
        };
    }

    /*
    * Guarded patterns
    * To avoid the need of additional if blocks in the switch,
    * and we can use the conditional logic in the case labels
     */
    private double getDoubleValueUsingGuardedPatterns(Object o) {
        return switch (o) {
            case String s && s.length() > 0 -> Double.parseDouble(s);
            default -> 0d;
        };
    }

    /*
     * order of the cases matters
     * String is a subclass of CharSequence
     */
    private double getDoubleUsingSwitch(Object o) {
        return switch (o) {
            case String s -> Double.parseDouble(s);
            case CharSequence c -> Double.parseDouble(c.toString());
            //case Object ob -> 0d;
            default -> 0d;
        };
    }

    @Test
    public void should_test_old_style_switch_with_yield() {
        // given
        var fruit = FruitType.APPLE;
        var expected = "Common fruit";

        // when
        var result = switch (fruit) {
            case APPLE, PEAR:
                yield "Common fruit";
            case ORANGE, AVOCADO:
                yield "Exotic fruit";
            default:
                yield "Undefined fruit";
        };

        // then
        assertThat(result).isEqualTo(expected);
    }

    // In Preview mode
    // Type pattern
    private static String formatterPatternSwitch(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            case null      -> "Null";
            default        -> o.toString();
        };
    }

    @Test
    public void should_format_object() {
        // given
        var i = 100;
        // when
        var result = formatterPatternSwitch(i);
        // then
        assertThat(result)
                .isEqualTo("int 100");

        // given
        var decimal = BigDecimal.TEN;
        // when
        var decimalResult = formatterPatternSwitch(decimal);
        assertThat(decimalResult)
                .isEqualTo("10");
    }

    enum FruitType {
        APPLE, PEAR, ORANGE, AVOCADO
    }
}
