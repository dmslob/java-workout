package com.dmslob.strategy.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        // GoF Strategy pattern
        LOGGER.info("Green dragon spotted ahead!");
        DragonSlayer dragonSlayer = new DragonSlayer(new MeleeStrategy());
        dragonSlayer.goToBattle();

        LOGGER.info("Red dragon emerges.");
        dragonSlayer.changeStrategy(new ProjectileStrategy());
        dragonSlayer.goToBattle();

        LOGGER.info("Black dragon lands before you.");
        dragonSlayer.changeStrategy(new SpellStrategy());
        dragonSlayer.goToBattle();

        // Java 8 Strategy pattern
        LOGGER.info("Green dragon spotted ahead!");
        dragonSlayer = new DragonSlayer(
                () -> LOGGER.info("With your Excalibur you severe the dragon's head!")
        );
        dragonSlayer.goToBattle();

        LOGGER.info("Red dragon emerges.");
        dragonSlayer.changeStrategy(
                () -> LOGGER.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!")
        );
        dragonSlayer.goToBattle();

        LOGGER.info("Black dragon lands before you.");
        dragonSlayer.changeStrategy(
                () -> LOGGER.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!")
        );
        dragonSlayer.goToBattle();
    }
}
