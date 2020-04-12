package com.dmslob.strategy.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpellStrategy implements DragonSlayingStrategy {

    private static final Logger LOGGER = LogManager.getLogger(SpellStrategy.class);

    @Override
    public void execute() {
        LOGGER.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!");
    }
}
