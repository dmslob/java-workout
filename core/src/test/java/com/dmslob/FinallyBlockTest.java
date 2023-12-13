package com.dmslob;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinallyBlockTest {

	@Test
	public void should_invoke_finally_block_when_try_return_value() {
		// given
		String expectedResultFromTry = "try";
		// when
		String resultFromTry = getResultFromTry();
		// then
		assertThat(resultFromTry).isEqualTo(expectedResultFromTry);
	}

	private String getResultFromTry() {
		String result;
		try {
			result = "try";
			return result;
		}
		finally {
			result = "finally";
		}
	}

	@Test
	public void should_invoke_finally_block() {
		// given
		String expectedResult = "finally";
		// when
		String result = getResult();
		// then
		assertThat(result).isEqualTo(expectedResult);
	}

	private String getResult() {
		String result;
		try {
			result = "try";
		}
		finally {
			result = "finally";
		}
		return result;
	}

	@Test
	public void should_not_invoke_finally_when_system_exit() {
		// when
		try {
			log.info("try block");
			System.exit(0);
		}
		finally {
			log.info("finally block");
		}
	}

	@Test
	public void should_catch_exception_even_if_system_exit_next() {
		// given
		int a = 20, b = 0;
		int result;
		// when
		try {
			result = a / b;
			System.exit(0);
		}
		catch (ArithmeticException e) {
			log.error("{}", e.getMessage());
		}
		finally {
			result = 0;
			log.info("finally block");
		}
		// then
		assertThat(result).isZero();
	}

	@Test
	public void should_not_invoke_statement_in_finally_if_exception() {
		int a = 20, b = 0;
		int result;
		// when
		try {
			result = a / b;
		}
		catch (ArithmeticException e) {
			log.error("{}", e.getMessage());
		}
		finally {
			goingToThrowException();
			log.info("finally block");
		}
	}

	private void goingToThrowException() {
		throw new RuntimeException("Ups...");
	}

	@Test
	public void should_return_value_from_finally() {
		// when
		int resultFromFinally = getResultFromFinally();
		// then
		assertThat(resultFromFinally).isOne();
	}

	private static int getResultFromFinally() {
		try {
			return 0;
		}
		finally {
			return 1;
		}
	}

	@Test
	public void should_not_execute_finally_when_main_thread_finishes_before_daemon_thread() throws InterruptedException {
		// given
		Thread daemon = new Thread(() -> {
			try {
				while (true) {
					Thread.sleep(10);
				}
			}
			catch (Exception e) {
				log.error("{}", e.getMessage());
			}
			finally {
				log.info("This will never be executed.");
			}
		});
		daemon.setDaemon(true);
		// when
		daemon.start();
		Thread.sleep(100);
	}

	@Test
	public void should_execute_finally_when_daemon_thread_throws_exception() throws InterruptedException {
		// given
		Thread daemon = new Thread(() -> {
			try {
				while (true) {
					Thread.sleep(10);
					throw new Exception("try block error");
				}
			}
			catch (Exception e) {
				log.error("{}", e.getMessage());
			}
			finally {
				log.info("Is executed");
			}
		});
		daemon.setDaemon(true);
		// when
		daemon.start();
		Thread.sleep(100);
	}
}
