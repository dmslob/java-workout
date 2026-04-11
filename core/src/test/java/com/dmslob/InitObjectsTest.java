package com.dmslob;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InitObjectsTest {

    @Test
    public void should_test_illegal_forward_references() {
        // given
        class Base {
            {
                //int j = i;    // ERROR: Illegal forward reference (reading)
                i = 10;         // This works (assignment)
                //int j = i;    // ERROR: Illegal forward reference (reading)
            }

            final int i;
        }
        // when
        var base = new Base();
        // then
        assertThat(base.i).isEqualTo(10);
    }

    /**
     * If your initializer block throws a checked exception (like IOException),
     * every constructor in that class must explicitly declare that it throws that exception.
     */
    @Test
    public void should_test_handling_checked_exceptions() {
        // given
        class Base {
            {
                if (true) throw new IOException("Checked exception in initializer");
            }

            public Base() throws IOException {
            }

            public Base(int a) throws IOException {
            }
        }
        // when
        // then
    }

    /**
     * Here might be Equality Problems:
     * The double brace initialization creates an anonymous inner class,
     * which means that the list of flavors is not an instance of ArrayList,
     * but rather an instance of a subclass of ArrayList.
     */
    @Test
    public void should_test_double_brace_initialization() {
        // given
        var pancake = new Pancake();
        // when
        var flavors = pancake.getFlavors();
        var codes = pancake.getCodes();
        // then
        // class com.dmslob.Pancake$1
        assertThat(flavors.getClass())
                .isNotEqualTo(ArrayList.class);
        // class java.util.ArrayList
        assertThat(codes.getClass())
                .isEqualTo(ArrayList.class);
    }
}

class Pancake {
    private final List<String> flavors = new ArrayList<>() {{
        add("Vanilla");
        add("Chocolate");
        add("Strawberry");
    }};
    private final List<String> codes = new ArrayList<>();

    public List<String> getFlavors() {
        return flavors;
    }

    public List<String> getCodes() {
        return codes;
    }
}
