package com.dmslob.diamondproblem;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiamondProblemTest {

    @Test
    public void should_return_A_when_resolved_manually() {
        // given
        var ab = new ABImpl();
        // when
        var actual = ab.foo();
        // then
        assertThat(actual).isEqualTo("A");
    }

    @Test
    public void should_return_BaseClass() {
        // given
        var subClass = new SubClass();
        // when
        var actual = subClass.foo();
        // then
        assertThat(actual).isEqualTo("BaseClass");
    }
}

interface A {
    default String foo() {
        return "A";
    }
}

interface B {
    default String foo() {
        return "B";
    }
}

// the compiler will issue an error
class ABImpl implements A, B {
    //So, we resolve the conflict manually
    public String foo() {
        return A.super.foo();
    }
}

// the method definition in the class is used
// and the interface definition is ignored.
class BaseClass {
    public String foo() {
        return "BaseClass";
    }
}

interface BaseInterface {
    default String foo() {
        return "BaseInterface";
    }
}

class SubClass extends BaseClass implements BaseInterface {
}