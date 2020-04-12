package com.dmslob.strategy.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeleeStrategy implements DragonSlayingStrategy {

    private static final Logger LOGGER = LogManager.getLogger(MeleeStrategy.class);

    @Override
    public void execute() {
        LOGGER.info("With your Excalibur you sever the dragon's head!");
    }
}
