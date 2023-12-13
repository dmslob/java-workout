package com.dmslob.exception;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TryWithResourcesTest {

	@Test
	public void should_invoke_close_method() {
		// given | when
		String errorMessage = null;
		try (var resource = new ResourceThrowingException()) {
			resource.use();
		}
		catch (Exception e) {
			errorMessage = e.getMessage();
			log.error("{}", errorMessage);
		}
		// then
		assertThat(errorMessage)
				.isEqualTo("Exception inside use()");
	}

	@Test
	public void should_get_exception_from_use_when_close_failed() {
		// given | when
		String errorMessage = null;
		try (var resource = new ResourceThrowingExceptionInClose()) {
			resource.use();
		}
		catch (Exception e) {
			errorMessage = e.getMessage();
			log.error("{}", errorMessage);
		}
		// then
		assertThat(errorMessage)
				.isEqualTo("Exception inside use()");
	}

	@Test
	public void should_get_suppressed_exceptions_when_close_failed() {
		// given | when
		String errorMessage = null;
		List<Throwable> suppressedExceptions = null;

		try (var resource = new ResourceThrowingExceptionInClose()) {
			resource.use();
		}
		catch (Exception e) {
			errorMessage = e.getMessage();
			log.error("{}", errorMessage);
			suppressedExceptions = getSuppressedExceptions(e);
		}

		// then
		assertThat(errorMessage)
				.isEqualTo("Exception inside use()");

		assertThat(suppressedExceptions)
				.isNotNull()
				.hasSize(1);

		assertThat(suppressedExceptions.get(0).getMessage())
				.isEqualTo("Exception inside close()");
	}

	private List<Throwable> getSuppressedExceptions(Exception e) {
		return Arrays.asList(e.getSuppressed());
	}
}

@Slf4j
class ResourceThrowingException implements AutoCloseable {

	public void use() {
		log.info("use()");
		throw new RuntimeException("Exception inside use()");
	}

	@Override
	public void close() throws Exception {
		log.info("close()");
	}
}

@Slf4j
class ResourceThrowingExceptionInClose implements AutoCloseable {

	public void use() {
		log.info("use()");
		throw new RuntimeException("Exception inside use()");
	}

	@Override
	public void close() throws Exception {
		log.info("close()");
		throw new RuntimeException("Exception inside close()");
	}
}
