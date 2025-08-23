package com.dmslob.java21;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * To run tests we need to add: --add-modules jdk.incubator.vector
 * for run configuration and for settings->java compiler additional cmd params
 */
public class VectorApiTest {
    private static final VectorSpecies<Float> species = FloatVector.SPECIES_256;

    @Test
    public void should_add_two_arrays() {
        // given
        int length = 8; // Number of elements in the arrays
        float[] array1 = new float[length];
        float[] array2 = new float[length];
        float[] result = new float[length];
        // Initialize arrays with random values
        Arrays.fill(array1, (float) Math.random());
        Arrays.fill(array2, (float) Math.random());
        // when
        // Perform addition using Vector API
        int i = 0;
        for (; i < length - species.length(); i += species.length()) {
            FloatVector a = FloatVector.fromArray(species, array1, i);
            FloatVector b = FloatVector.fromArray(species, array2, i);
            FloatVector sum = a.add(b);
            sum.intoArray(result, i);
        }
        System.out.println(STR."Result: \{Arrays.toString(result)}");

        for (; i < length; i++) {
            result[i] = array1[i] + array2[i];
        }
        // then
        System.out.println(STR."Result: \{Arrays.toString(result)}");
    }

    @Test
    public void should_calculate_dot_product() {
        // given
        int length = 8; // Number of elements in the arrays
        float[] array1 = new float[length];
        float[] array2 = new float[length];
        // Initialize arrays with random values
        Arrays.fill(array1, (float) Math.random());
        Arrays.fill(array2, (float) Math.random());
        // when
        // Perform dot product using Vector API
        VectorSpecies<Float> species = FloatVector.SPECIES_256;
        int i = 0;
        FloatVector sum = (FloatVector) species.zero();
        for (; i < length - species.length(); i += species.length()) {
            FloatVector a = FloatVector.fromArray(species, array1, i);
            FloatVector b = FloatVector.fromArray(species, array2, i);
            sum = sum.add(a.mul(b));
        }
        float dotProduct = sum.reduceLanes(VectorOperators.ADD);
        for (; i < length; i++) {
            dotProduct += array1[i] * array2[i];
        }
        System.out.println(STR."Dot Product: \{dotProduct}");
    }
}
