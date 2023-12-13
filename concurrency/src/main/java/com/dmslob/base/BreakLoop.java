package com.dmslob.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BreakLoop {
    private static final Logger logger = LogManager.getLogger(BreakLoop.class);

    public static void main(String[] args) {
        BreakLoop breakLoop = new BreakLoop();
        breakLoop.breakOuterLoop();
        breakLoop.breakInnerLoop();
    }

    void breakOuterLoop() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 2) {
                    logger.info("Breaking");
                    return;
                }
                logger.info(i + " " + j);
            }
        }
    }

    void breakInnerLoop() {
        outerloop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i * j > 6) {
                    logger.info("Breaking");
                    break outerloop;
                }
                logger.info(i + " " + j);
            }
        }
        logger.info("Done");
    }
}
