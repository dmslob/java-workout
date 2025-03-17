package com.dmslob.collections;

import java.util.function.Supplier;

public class LazyList<T> {
	final T head;
	final Supplier<T> tail;

	public LazyList(T head, Supplier<T> tail) {
		this.head = head;
		this.tail = tail;
	}

	public T head() {
		return head;
	}

	public T tail() {
		return tail.get();
	}

	public boolean isEmpty() {
		return false;
	}
}
