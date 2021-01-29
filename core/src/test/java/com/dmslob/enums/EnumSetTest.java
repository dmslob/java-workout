package com.dmslob.enums;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EnumSetTest {

    @Test
    public void shouldBeRemoved() {
        int expectedSizeOfColors = 5;
        EnumSet<Color> colors = EnumSet.allOf(Color.class);
        colors.remove(Color.BLACK);
        int actualSizeOfColors = colors.size();

        Assert.assertEquals(expectedSizeOfColors, actualSizeOfColors);
    }

    @Test
    public void shouldCreateAnEmptyEnumSet() {
        int expectedSize = 0;
        EnumSet<Color> emptySetOfColors = EnumSet.noneOf(Color.class);
        int actualSize = emptySetOfColors.size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldCreateSubsetOfEnum() {
        EnumSet<Color> expectedColors = EnumSet.of(Color.YELLOW, Color.GREEN, Color.BLUE);
        EnumSet<Color> rangeOfColors = EnumSet.range(Color.YELLOW, Color.BLUE);

        Assert.assertArrayEquals(expectedColors.toArray(), rangeOfColors.toArray());
    }

    public void test() {
        //  to exclude the elements passed as parameters
        EnumSet<Color> excludeColors = EnumSet.complementOf(EnumSet.of(Color.BLACK, Color.WHITE));
        System.out.println();
        excludeColors.forEach(color -> System.out.println(color));

        // we can create an EnumSet by copying all the elements from another EnumSet
        EnumSet<Color> copyColors = EnumSet.copyOf(EnumSet.of(Color.BLACK, Color.WHITE));
        System.out.println();
        copyColors.forEach(color -> System.out.println(color));
        // or
        List<Color> colorsList = new ArrayList<>();
        colorsList.add(Color.RED);
        EnumSet<Color> listCopy = EnumSet.copyOf(colorsList);
        System.out.println();
        listCopy.forEach(color -> System.out.println(color));
        // or
        listCopy.forEach(System.out::println);

        System.out.println(copyColors.contains(Color.BLACK));
    }
}
