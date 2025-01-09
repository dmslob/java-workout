package com.dmslob.types;

import org.junit.Assert;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IntegerTest {
    @Test
    public void should_have_negative_result() {
        // given
        int x = Integer.MAX_VALUE + 10;
        // when | then
        Assert.assertTrue(x < 0);

        var a  = new Object();
        var b = new Object();
        assertThat(a.hashCode()).isNotEqualTo(b.hashCode());

        class A {
            boolean f;
        }

        // https://github.com/openjdk/jol?tab=readme-ov-file
        //System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    @Test
    public void should_throw_error_when_cast_to_object() {
        // given
        Object number = 12;
        // then
        assertThat(number).isEqualTo(12);
        // when
        assertThatThrownBy(() -> {
            Integer integer = (Integer) new Object();
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    public void should_cast_to_first_type() {
        char x = 'X';
        int i = 0;
        System.out.println(true ? x : 0);
        System.out.println(false ? i : x);

        System.out.println("H" + "a");
        System.out.println('H' + 'a');

        // \u0022 is the Unicode escape for double-quote (")
        System.out.println("a\u0022.length() + \u0022b".length());

        System.out.print("iexplore:");
        http://www.google.com;
        System.out.println(":maximize");
    }

    @Test
    public void should_test_loop() {
        loopPuzzle();
    }

    public void loopPuzzle() {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            j = j++;
        }
        System.out.println(j);

        int count = 0;
        for (int i = 0; i < 10; i++) {
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void foo() {
        final int START = 2000000000;
        float a = START;
        System.out.println(a);
        float r = START + 50;
        System.out.println(r);

        int count = 0;
        for (float f = START; f < r; f++) {
            count++;
            System.out.println(count);
        }
        System.out.println(count);
    }
}