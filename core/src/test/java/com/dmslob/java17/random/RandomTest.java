package com.dmslob.java17.random;

import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://openjdk.org/jeps/356">JEP 356: Enhanced Pseudo-Random Number Generators</a>
 * <p>Provide new interface types and implementations for pseudorandom number generators (PRNGs)</p>
 * <p>Most of the new generator implementations are not thread-safe.
 * However, both Random and SecureRandom still are.</p>
 */
public class RandomTest {

    @Test
    public void should_show_randoms() {
        RandomGeneratorFactory.all()
                .sorted(Comparator.comparing(RandomGeneratorFactory::name))
                .forEach(factory -> System.out.printf("%s\t\t%s\t\t%s\t\t%s%n",
                        factory.group(),
                        factory.name(),
                        factory.isJumpable(),
                        factory.isSplittable()));
    }

    @Test
    public void should_generate_random_numbers_by_new_api() {
        // given
        var generator = RandomGenerator.getDefault(); // L32X64MixRandom
        //var generator = RandomGenerator.of("L128X256MixRandom");
        // when
        var nextInt = generator.nextInt(10);
        // then
        assertThat(nextInt)
                .isPositive()
                .isLessThan(10);
    }

    @Test
    public void should_generate_random_numbers_by_old_api() {
        // given
        var random = new Random();
        // when
        int number = random.nextInt(10);
        // then
        assertThat(number)
                .isPositive()
                .isLessThan(10);
    }
}
