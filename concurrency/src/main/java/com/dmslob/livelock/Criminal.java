package com.dmslob.livelock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Criminal {
	private static final Logger log = LogManager.getLogger(Criminal.class);

	private boolean hostageReleased = false;

	public void tryToReleaseHostage(Police police) {
		while (!police.isMoneySent()) {
			waitForRansom();
		}
		log.info("Criminal released hostage");
		this.hostageReleased = true;
	}

	private void waitForRansom() {
		log.info("Criminal is waiting police to give ransom");
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	boolean isHostageReleased() {
		return this.hostageReleased;
	}
}
