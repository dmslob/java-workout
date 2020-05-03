package com.dmslob.conditions;

import com.dmslob.oca.overriding.OverloadMethod;
import org.junit.Assert;
import org.junit.Test;

// https://blogs.oracle.com/javamagazine/quiz-yourself-overriding-operator-precedence-intermediate
public class TernaryOperationTest {

    /**
     * The effect of combining an int literal with a char describes the resulting type as char | bnp(int,char).
     * This means that if the literal fits in the char data size, the result is a char; otherwise,
     * it’s the result of normal promotion (which would be int).
     */
    @Test
    public void returnCharTest() {
        byte b = 0;
        char c = 'A';

        Assert.assertEquals("Char", OverloadMethod.test(true ? 0 : 'A'));
    }

    /**
     * Rules:
     * - If either operand is of type double, the other is converted to double.
     * - Otherwise, if either operand is of type float, the other is converted to float.
     * - Otherwise, if either operand is of type long, the other is converted to long.
     * - Otherwise, both operands are converted to type int.
     */
    @Test
    public void returnIntTest() {
        byte b = 0;
        char c = 'A';
        String expectedResult = "Int";

        Assert.assertEquals(expectedResult, OverloadMethod.test(true ? b : c));
        Assert.assertEquals(expectedResult, OverloadMethod.test(false ? b : c));
        Assert.assertEquals(expectedResult, OverloadMethod.test(false ? 'A' : (byte) 0));
    }
}
