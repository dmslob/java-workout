package com.dmslob.functional;

import org.testng.annotations.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class MonadTest {

    @Test
    public void should_create_monad() {
        // given
        Function<Integer, Integer> counter = a -> a + 1;
        var monad = Monad.from(0);
        var expected = "2";
        // when
        var counted = monad
                .map(counter)
                .map(counter);
        // then
        assertThat(counted.toString())
                .isEqualTo(expected);
    }
}
