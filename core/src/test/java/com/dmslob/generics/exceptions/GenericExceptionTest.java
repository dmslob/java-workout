package com.dmslob.generics.exceptions;

public class GenericExceptionTest {
}

// compile error
// may not to extend java.lang.Throwable
// class MyException<T> extends Exception {}

class GenericClassWithInnerClass<T> {
	// compile error
	// may not to extend java.lang.Throwable
	//private class MyInnerException extends Exception {}

	// OK
	private static class MyInnerException extends Exception {
	}
}

abstract class Processor<T extends Throwable> {
	abstract void process() throws T; // OK
	private T t; // OK

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

	// compile error
	// Cannot be referenced from a static context
	//private static T t1;

	void doThrow(T ex) throws T {
		throw ex; // OK
	}

	// Cannot be referenced from the static context
	//	static void doThrow(T ex) throws T {
	//		throw ex;
	//	}
}
