package com.dmslob.java17;

import org.testng.annotations.Test;

import java.awt.*;
import java.util.function.Consumer;

public class PatternMatchingForInstanceOfTest {

    @Test
    public void should_test_pattern_matching_scope() {
        // given
        Object o = new GrapeClass(Color.BLUE, 2);
        if (o instanceof GrapeClass grape && grape.numberOfPits() == 2) {
            System.out.println("This grape has " + grape.numberOfPits() + " pits.");
        } else {
            throw new RuntimeException();
        }
        System.out.println("This grape has " + grape.numberOfPits() + " pits.");
    }

    record GrapeClass(Color color, int numberOfPits) {
    }

    @Test
    public void should_compile_when_instanceof() {
        // given
        interface NonLiving {
        }
        class Plant {
        }
        // it is feasible for a subclass of Plant to implement the interface NonLiving.
        // Thus, the following code compiles successfully.
        Consumer<Plant> consumer = (Plant p) -> {
            if (p instanceof NonLiving) {
                System.out.println(p);
            }
        };
    }

    @Test
    public void should_not_compile_when_instanceof_because_of_final_class() {
        // given
        interface NonLiving {
        }
        final class Plant {
        }
        // Plant can no longer be extended, the compiler is sure there won’t be any
        // Plant instance that implements the interface NonLiving,
        Consumer<Plant> consumer = (Plant p) -> {
            //if (p instanceof NonLiving) {} // Compile error!
        };
    }

    interface NonLiving {}

    sealed class Plant permits Herb, Climber {}

    final class Herb extends Plant {}

    sealed class Climber extends Plant permits Cucumber  {}

    final class Cucumber extends Climber {}

    @Test
    public void should_not_compile_when_instanceof_because_of_sealed_class() {
        // given
        // Plant is sealed and all its derived classes are either
        // sealed or final and none of them implements the interface NonLiving.
        Consumer<Plant> consumer = (Plant p) -> {
            //if (p instanceof NonLiving) {} // Compile error!
        };
        // However, if you open the hierarchy by defining any of its subclasses,
        // say, Herb, as a non-sealed class, the instanceof will compile because the
        // compiler can’t ensure that none of the Plant instances would implement
        // the interface NonLiving. Thus, a class that extends non-sealed class Herb might
        // implement the interface NonLiving,
    }
}
