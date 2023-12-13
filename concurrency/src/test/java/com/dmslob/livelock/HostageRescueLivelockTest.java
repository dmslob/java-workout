package com.dmslob.livelock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class HostageRescueLivelockTest {
	@Test
	public void shoud_test() {
		//given
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Police police = new Police();
		Criminal criminal = new Criminal();

		//when | then
		while (!criminal.isHostageReleased()) {
			pool.execute(() -> police.tryToGiveRansom(criminal));
			pool.execute(() -> criminal.tryToReleaseHostage(police));
		}
	}
}
