package com.dmslob.lambdas;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;
import java.util.function.Function;

// When working with streams, we probably don't want to stop processing the stream if an exception occurs.
// If your stream contains a very large amount of items that need to be processed, do you want that stream to terminate when for instance the second item throws an exception? Probably not.
// Let's turn our way of thinking around. Why not consider "the exceptional situation" just as much as a possible result as we would for a "successful" result.
// Let's consider it both as data, continuing to process the stream, and decide afterward what to do with it. We can do that, but to make it possible,
// we need to introduce a new type - the Either type.
// The Either type is a common type in functional languages and not (yet) part of Java. Similar to the Optional type in Java, an Either is a generic wrapper with two possibilities.
// It can either be a Left or a Right but never both. Both left and right can be of any types. For instance, if we have an Either value,
// this value can either hold something of type String or of type Integer, Either<String,Integer>.
// If we use this principle for exception handling, we can say that our Either  type holds either an  Exception or a value. By convenience, normally,
// the left is the Exception and the right is the successful value. You can remember this by thinking of the right as not only the right-hand side but also as a synonym for “good,” “ok,” etc.
public class Either<L, R> {

    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> Left(L value) {
        return new Either(value, null);
    }

    public static <L, R> Either<L, R> Right(R value) {
        return new Either(null, value);
    }

    public static <T, R> Function<T, Either> lift(CheckedFunction<T, R> function) {
        return t -> {
            try {
                return Either.Right(function.apply(t));
            } catch (Exception ex) {
                return Either.Left(ex);
            }
        };
    }

    public static <T, R> Function<T, Either> liftWithValue(CheckedFunction<T, R> function) {
        return t -> {
            try {
                return Either.Right(function.apply(t));
            } catch (Exception ex) {
                return Either.Left(Pair.of(ex, t));
            }
        };
    }

    public Optional<L> getLeft() {
        return Optional.ofNullable(left);
    }

    public Optional<R> getRight() {
        return Optional.ofNullable(right);
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public <T> Optional<T> mapLeft(Function<? super L, T> mapper) {
        if (isLeft()) {
            return Optional.of(mapper.apply(left));
        }
        return Optional.empty();
    }

    public <T> Optional<T> mapRight(Function<? super R, T> mapper) {
        if (isRight()) {
            return Optional.of(mapper.apply(right));
        }
        return Optional.empty();
    }

    public String toString() {
        if (isLeft()) {
            return "Left(" + left + ")";
        }
        return "Right(" + right + ")";
    }
}
