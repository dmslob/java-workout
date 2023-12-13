package com.dmslob.types;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClassTest {
    @Test
    public void should_test_A_class() {
        // given
        A a = new A();
        // when
        // a.class; // Compile error! Unknown class: "a"
        // Class<A> classA = a.getClass(); // Compilation error!
        // Class<A> classA = (Class<A>) a.getClass();
        Class<? extends A> aClass = a.getClass();
        Class<A> AClass = A.class;
        // then
        assertThat(aClass).isEqualTo(AClass);
    }

    @Test
    public void should_test_B_class() {
        // given
        A b = new B();
        // when
        Class<? extends A> bClass = b.getClass();
        Class<B> BClass = B.class;
        // then
        assertThat(bClass).isEqualTo(BClass);
    }
}

class A {

}

class B extends A {

}
