package com.dmslob.generics.constructor;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericConstructorTest {

    @Test
    public void should_construct_RankEntry_when_rankable_object() {
        // given
        Product product = new Product("milk", 2.5, 30);

        // when
        // we can also have a constructor with a generic type
        // that's different from the class' generic type.
        RankEntry entry = new RankEntry(product);

        // then
        assertThat(entry.getData()).isEqualTo("Product{name='milk', price=2.5, sale=30}");
        assertThat(entry.getRank()).isEqualTo(30);
    }

    @Test
    public void should_not_compile_when_not_rankable_object() {
        //Compile error - String is not Rankable
        //RankEntry entry = new RankEntry("Milk");
    }
}
