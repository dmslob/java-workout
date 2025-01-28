package com.dmslob.generics.exceptions;

import java.util.List;

public class GenericExceptionTest {

}

// compile error
// may not to extend java.lang.Throwable
// class MyException<T> extends Exception {}

class GenericClassWithInnerClass<T> {
	// compile error
	// may not to extend java.lang.Throwable
	// private class MyInnerException extends Exception {}

	// OK
	private static class MyInnerException extends Exception {
	}
}

abstract class Seq<E> implements List<E> {
	// compile error
	// Illegal generic type for instanceof
	// static <T> boolean isSeq(List<T> list) {
	//		return list instanceof Seq<T>;
	// }

	// compile error
	// static <E> boolean isElement(E o) {
	//		return o instanceof E;
	// }

	static <E> boolean isSeq(E o) {
		return o instanceof Seq; // OK
	}

	static <E> boolean isSeqArray(E x) {
		return x instanceof Seq[]; // OK
	}
}

abstract class Processor<T extends Throwable> {
	abstract void process() throws T; // OK

	// OK
	public <T extends Exception> void throwIt(T t) throws T {
		throw t;
	}

	public void catchIt() {
		try {
			throwIt(new Exception());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//	public <T extends Exception> void catchIt(T t) {
	//		try {
	//			throwIt(t); // OK
	//		}
	//		// compile error
	//		// Cannot catch type parameters
	//		catch (T e) {
	//		}
	//	}

	private T t; // OK

	// compile error
	// Cannot be referenced from a static context
	//private static T t1;

	void doThrow(T ex) throws T {
		throw ex; // OK
	}

	// Cannot be referenced from the static context
	//	static void doThrow(T except) throws T {
	//		throw except;
	//	}
}
