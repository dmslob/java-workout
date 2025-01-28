package com.dmslob.exceptions;

import org.testng.annotations.Test;

public class UncaughtExceptionTest {

	@Test
	public void should_caught_exception_globally() {
		// given
		Thread.setDefaultUncaughtExceptionHandler(new LastChanceHandler());
		Thread t1 = new Thread(new ThreadThrowingException());
		Thread t2 = new Thread(new ThreadThrowingException());

		// when
		t1.start();
		t2.start();
	}

	@Test
	public void should_caught_exception_per_thread() {
		// given
		Thread newThread = new Thread(new ThreadThrowingException());
		newThread.setUncaughtExceptionHandler((thread, e) ->
				System.out.printf("%s throws exception: %s", thread.getName(), e.getMessage())
		);

		// when
		newThread.start();
	}

	@Test
	public void should_caught_exception_per_thread_group() {
		// given
		var threadGroup = new ThreadGroup("Admins");
		Thread t1 = new Thread(threadGroup, new ThreadThrowingException());
		t1.setUncaughtExceptionHandler(threadGroup);

		Thread t2 = new Thread(threadGroup, new ThreadThrowingException());

		// when
		t1.start();
		t2.start();
	}
}

class ThreadThrowingException implements Runnable {
	@Override
	public void run() {
		throw new RuntimeException("Some exception");
	}
}

class LastChanceHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread thread, Throwable e) {
		System.out.printf("%s throws exception: %s\n", thread.getName(), e.getMessage());
	}
}
