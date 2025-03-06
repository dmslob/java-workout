package com.dmslob.lambdas;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaTest {

	@Test
	public void should_increment_initial_value() {
		// given
		var counter = counter();
		// when | then
		assertThat(counter.apply(1)).isEqualTo(1);
		assertThat(counter.apply(1)).isEqualTo(2);
		assertThat(counter.apply(1)).isEqualTo(3);
	}

	// example of closure
    Function<Integer, Integer> counter() {
        var atomic = new AtomicInteger(0);
        return atomic::addAndGet;
    }
}
