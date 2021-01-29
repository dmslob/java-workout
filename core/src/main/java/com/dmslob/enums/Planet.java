package com.dmslob.enums;

public enum Planet {
    VENUS(4000) {
        public int getRadius() {
            return this.getSize() / 2;
        }
    },
    MERCURY(2000) {
        public int getRadius() {
            return this.getSize() / 2;
        }
    };

    static {
        System.out.println("Planet's static block");
    }

    private final int size;

    abstract int getRadius();

    Planet(int size) {
        this.size = size;
        System.out.printf("Planet size: %d%n", size);
    }

    public int getSize() {
        return size;
    }

    public static Planet valueOfPlanet(String label) {
        for (Planet planet : values()) {
            if (planet.name().equals(label)) {
                return planet;
            }
        }
        return null;
    }
}
