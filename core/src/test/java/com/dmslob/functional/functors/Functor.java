package com.dmslob.functional.functors;

import java.util.function.Function;

/**
 * A functor is a typed data structure that encapsulates some value(s).
 * The only operation that functor provides is map() that takes a function f.
 * This function receives whatever is inside a box,
 * transforms it and wraps the result as-is into a second functor.
 * Functor<T> is always an immutable container, thus map never mutates original
 * object it was executed on. Instead, it returns the result wrapped in a brand-new functor,
 * possibly of different type R.
 * Additionally functors should not perform any actions when identity function is applied, that is map(x -> x).
 * Such a pattern should always return either the same functor or an equal instance.
 */
public interface Functor<T, F extends Functor<?, ?>> {
    <R> F map(Function<T, R> f);
}
