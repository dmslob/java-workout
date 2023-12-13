package com.dmslob.lazyassignment;

import java.util.function.Supplier;

public class LazyVar<T> implements Supplier<T> {
	private final Supplier<T> supplier;
	private boolean supplied = false;
	private T value;

	private LazyVar(Supplier<T> supplier) {
		this.supplier = supplier;
	}

	public static <T> LazyVar<T> let(Supplier<T> supplier) {
		return new LazyVar<T>(supplier);
	}

	@Override
	public T get() {
		if (supplied) {
			return value;
		}
		supplied = true;
		return value = (T) supplier.get();
	}
}
