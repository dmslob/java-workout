package com.dmslob.livelock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Police {

	private static final Logger log = LogManager.getLogger(Police.class);

	private boolean moneySent = false;

	public void tryToGiveRansom(Criminal criminal) {
		while (!criminal.isHostageReleased()) {
			waitForHostage();
		}
		log.info("Police sent money");
		this.moneySent = true;
	}

	private void waitForHostage() {
		log.info("Police is waiting criminal to release hostage");
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	boolean isMoneySent() {
		return this.moneySent;
	}
}
