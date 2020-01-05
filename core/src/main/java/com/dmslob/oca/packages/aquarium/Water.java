package com.dmslob.oca.packages.aquarium;

public class Water {

    boolean salty = false;

    protected Water(boolean flag) {
        System.out.println("Water");
        this.salty = flag;
    }
}

class NewWater {

    public void flow() {
        Water water = new Water(false);
    }
}