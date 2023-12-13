package com.dmslob.java17.sealed;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Sealed classes and their implementations can’t span multiple Java modules.
 * If a sealed base class is declared in a named Java module,
 * all its implementations must be defined in the same module.
 * However, the classes can appear in different packages.
 * For a sealed class declared in an unnamed Java module,
 * all its implementations must be defined in the same package.
 * <p>
 * We can declare interfaces as sealed as well
 * sealed : the class can be extended by the defined classes using permits
 * non-sealed : any class can extend this class
 * final : the class cannot be extended
 */
public class SealedTest {

    @Test
    public void should_test_sealed_classes() {
        // given
        var herb = new Herb();
        var shrub = new Shrub();
        var climber = new Climber();
        Plant plant = herb;
    }

    // with interfaces
    // An implementing class must be either sealed, or non-sealed.
    non-sealed interface SportCar extends Car {
    }

    sealed interface Car permits SportCar, Jeep {
    }

    // Records can implement a sealed interface,
    // records are implicitly final,
    // by the way, implicitly extend the java.lang.Record class,
    // so records can’t extend sealed classes.
    record Jeep() implements Car {
    }

    // with classes
    public abstract sealed class Plant permits Herb, Shrub, Climber {
    }

    // An implementing class must be either
    // final, sealed, or non-sealed.
    public final class Shrub extends Plant {
    }

    public non-sealed class Herb extends Plant {

    }

    public class Mint extends Herb {
    }

    public sealed class Climber extends Plant permits Cucumber {
    }

    public final class Cucumber extends Climber {
    }

    // Compile error !!! no permission
    // class AquaticPlant extends Plant {}

    @Test
    public void should_check_permitted_classes() {
        // given
        var aClass = Plant.class;

        // when | then
        assertThat(aClass.isSealed()).isTrue();
        var permittedClasses = aClass.getPermittedSubclasses();

        assertThat(permittedClasses).isNotNull();
        assertThat(permittedClasses).hasSize(3);

        Arrays.stream(aClass.getPermittedSubclasses())
                .forEach(System.out::println);
    }

    public sealed interface Shape permits Polygon { }
    public non-sealed interface Polygon extends Shape { }
    public final class UtahTeapot { }
    public class Ring { }

    public void work(Shape s) {
        //UtahTeapot u = (UtahTeapot) s;  // Error
        Ring r = (Ring) s;              // Permitted
    }
}

/**
 * If you define a sealed class and its derived classes in the same source file,
 * you can omit the explicit permits clause; the compiler will infer it for you.
 */
sealed class Gas {
}

final class Nitrogen extends Gas {
}

non-sealed class Oxygen extends Gas {
}
