package com.dmslob;

import org.testng.annotations.Test;

public class PrivateInterfaceMethodsTest {

	@Test
	public void foo() {
		new ImplOld().f();
		Old.g();
		new ImplJDK9().f();
		JDK9.g();
	}
}

// {NewFeature} Since JDK 9
interface Old {
	default void fd() {
		System.out.println("Old::fd()");
	}

	static void fs() {
		System.out.println("Old::fs()");
	}

	default void f() {
		fd();
	}

	static void g() {
		fs();
	}
}

class ImplOld implements Old {
}

interface JDK9 {
	private void fd() { // Automatically default
		System.out.println("JDK9::fd()");
	}

	private static void fs() {
		System.out.println("JDK9::fs()");
	}

	default void f() {
		fd();
	}

	static void g() {
		fs();
	}
}

class ImplJDK9 implements JDK9 {
}
