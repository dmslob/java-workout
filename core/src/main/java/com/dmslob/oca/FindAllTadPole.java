package com.dmslob.oca;

import java.util.ArrayList;
import java.util.List;

public class FindAllTadPole {

    public static void main(String[] args) {

        List<Tadpole> tadpoles = new ArrayList<Tadpole>();
        for (Amphibian amphibian : tadpoles) {
            CanSwim tadpole1 = amphibian;
            Amphibian tadpole2 = amphibian;
            Object tadpole3 = amphibian;
        }
    }
}

interface CanSwim {
}

class Amphibian implements CanSwim {
}

class Tadpole extends Amphibian {
}

interface Creature {

    public default String getName() {
        return null;
    }

    //String getName();
}

interface Mammal {

    public default String getName() {
        return null;
    }

    //String getName();
}

abstract class Otter implements Mammal, Creature {

    //public abstract String getName();

    // or
    public String getName() {
        return null;
    }
}