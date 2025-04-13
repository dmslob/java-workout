package com.dmslob.generics;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ErasedTypeTest {

    @Test
    public void should_have_same_class() {
        // when
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        // then
        assertThat(c1).isEqualTo(c2);
    }
}