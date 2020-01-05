package com.dmslob.enums;

public class EnumSample {
    public static void main(String[] args) {
        System.out.println(Planet.MERCURY.getRadius());
        System.out.println(Planet.VENUS.getRadius());
    }
}

enum Planet {
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
        System.out.println("Static block");
    }

    private final int size;

    abstract int getRadius();

    Planet(int size) {
        this.size = size;
        System.out.println("Planet size: " + size);
    }

    public int getSize() {
        return size;
    }
}

enum Direction {
    UP {
        public Direction opposite() {
            return DOWN;
        }
    },
    DOWN {
        public Direction opposite() {
            return UP;
        }
    };

    public abstract Direction opposite();
}

enum Type {

    INT(true) {
        public Object parse(String string) {
            return Integer.valueOf(string);
        }
    },
    INTEGER(false) {
        public Object parse(String string) {
            return Integer.valueOf(string);
        }
    },
    STRING(false) {
        public Object parse(String string) {
            return string;
        }
    };

    boolean primitive;

    Type(boolean primitive) {
        this.primitive = primitive;
    }

    public boolean isPrimitive() {
        return primitive;
    }

    public abstract Object parse(String string);
}

