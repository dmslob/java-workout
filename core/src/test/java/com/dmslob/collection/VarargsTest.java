package com.dmslob.collection;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VarargsTest {

	@Test
	public void should_invoke() {
		filter(1, 2, 3);

		filterFrom(1, 2, 3, 4);
	}

	public void filter(int... nums) {
		log.info("{}", nums);
		// filtering ...
	}

	public void filterFrom(int start, int... nums) {
		log.info("{}", nums);
		// filtering ...
	}

	// public void filter(int... nums, int start) { } // DOES NOT COMPILE

	// public void filter(int... start, int... nums) { } // DOES NOT COMPILE
}
