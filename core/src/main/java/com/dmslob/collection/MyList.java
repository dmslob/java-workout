package com.dmslob.collection;

public interface MyList<T> {
	T head();

	MyList<T> tail();

	default boolean isEmpty() {
		return true;
	}
}
