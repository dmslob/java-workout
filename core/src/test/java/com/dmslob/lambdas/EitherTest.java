package com.dmslob.lambdas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

public class EitherTest {

    @Test
    public void should_throw_exception_when_no_valid_name() {
        // given
        final List<String> names = names();
        String expectedErrorMessage = "No valid name";
        // when | then
        assertThatThrownBy(() -> names.stream()
                .map(wrap(this::validate))
                .collect(Collectors.toList()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(expectedErrorMessage);
    }

    @Test
    public void should_not_throw_exception_when_no_valid_name() {
        // given
        final List<String> names = names();
        String expectedErrorMessage = "No valid name";
        // when
        var validNames = names.stream()
                .map(Either.lift(this::validate))
                .collect(Collectors.toList());
        // then
        assertThat(validNames)
                .isNotEmpty()
                .hasSize(3);
        assertThat(validNames.get(0).getRight())
                .isNotNull()
                .isEqualTo(Optional.of("Bob"));
        assertThat(validNames.get(1).getRight())
                .isNotNull()
                .isEqualTo(Optional.of("Tom"));

        final Optional<Exception> exception = validNames.get(2).getLeft();

        assertThat(exception.isPresent()).isTrue();
        assertThat(exception.get()).isInstanceOf(Exception.class);
        assertThat(exception.get().getMessage()).isEqualTo(expectedErrorMessage);
    }

    private String validate(String s) throws Exception {
        if (s.isEmpty()) {
            throw new Exception("No valid name");
        }
        return s;
    }

    private <T, R> Function<T, R> wrap(CheckedFunction<T, R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    private List<String> names() {
        return new ArrayList<>(Arrays.asList("Bob", "Tom", ""));
    }
}
