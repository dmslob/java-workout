package com.dmslob.oop.polimorphism;

public class ClimbDemo {
    public static void main(String[] args) {

    }
}

interface CanClimb {
    public abstract void climb();
}

interface CanClimbTrees extends CanClimb {
}

abstract class Chipmunk implements CanClimbTrees {
    public abstract void chew();
}

class EasternChipmunk extends Chipmunk {
    public void chew() {
        System.out.println("Eastern Chipmunk is Chewing");
    }

    public void climb() {
        System.out.println("Has to be implemented");
    }
}
