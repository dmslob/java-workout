package com.dmslob.strategy.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProjectileStrategy implements DragonSlayingStrategy {

    private static final Logger LOGGER = LogManager.getLogger(ProjectileStrategy.class);

    @Override
    public void execute() {
        LOGGER.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!");
    }
}
