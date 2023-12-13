package com.dmslob.java17.records;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.testng.annotations.Test;

import java.awt.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RecordsTest {

    @Test
    public void should_test_equals_hashCode_record_methods() {
        // given
        record Owner(String name, int age, Pet cat) {
        }

        var kitty = new Pet("Kitty");
        var similarKitty = new Pet("Kitty");

        var tom = new Owner("Tom", 23, kitty);
        var anotherTom = new Owner("Tom", 23, similarKitty);

        // when
        var same = tom.equals(anotherTom);
        // then
        assertThat(same).isTrue();
    }

    @Test
    public void should_test_basic_record_with_validation() {
        // given
        record Grape(Color color, int nbrOfPits) {
            Grape {
                System.out.println("Parameter color=" + color + ", Field color=" + this.color());
                System.out.println("Parameter nbrOfPits=" + nbrOfPits + ", Field nbrOfPits=" + this.nbrOfPits());
                if (color == null) {
                    throw new IllegalArgumentException("Color may not be null");
                }
            }
        }

        Grape grape = new Grape(Color.BLUE, 1);
        System.out.println("Grape 1 is " + grape);
    }
//    Compile error
//    private static class ExtendedRecord extends java.lang.Record {
//
//    }

    private static class Pet {
        private String name;

        public Pet(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pet pet = (Pet) o;
            return new EqualsBuilder().append(name, pet.name).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(name).toHashCode();
        }
    }
}
