package com.dmslob.oca.packages;

import com.dmslob.oca.packages.aquarium.Water;

public class Main {

    public static void main(String[] args) {
        new HolyWater();

        // Water water = new Water(false); //compile error, protected access
    }
}

class HolyWater extends Water {

    public HolyWater() {
        super(true);
        System.out.println("HolyWater");
    }
}