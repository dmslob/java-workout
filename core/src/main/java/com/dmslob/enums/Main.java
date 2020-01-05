package com.dmslob.enums;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        enumSet();
        enumMap();
    }

    static void enumMap() {
        EnumMap<DayOfWeek, String> activityMap = new EnumMap<>(DayOfWeek.class);
        activityMap.put(DayOfWeek.MONDAY, "Soccer");
        activityMap.put(DayOfWeek.TUESDAY, "Basketball");
        // activityMap.put(Color.BLACK, "Basketball"); // compile error

        EnumMap<DayOfWeek, String> activityMapCopy = new EnumMap<>(activityMap);
        System.out.println(activityMapCopy.size());
        System.out.println(activityMapCopy.get(DayOfWeek.MONDAY));
        System.out.println(activityMapCopy.get(DayOfWeek.TUESDAY));
        System.out.println(activityMapCopy.containsKey(DayOfWeek.TUESDAY));
        System.out.println(activityMapCopy.containsValue("Basketball"));

        // null is a semantically valid value for EnumMap
        activityMapCopy.put(DayOfWeek.FRIDAY, null);
        System.out.println(activityMapCopy.get(DayOfWeek.FRIDAY));

        System.out.println("Values:");
        activityMapCopy.values().forEach(s -> System.out.println(s));

        System.out.println("KeySet:");
        activityMapCopy.keySet().forEach(dayOfWeek -> System.out.println(dayOfWeek));

        System.out.println("EntrySet:");
        activityMapCopy.entrySet()
                .forEach(dayOfWeekStringEntry -> System.out.println(dayOfWeekStringEntry.getKey()
                        + " : " + dayOfWeekStringEntry.getValue()));

        EnumMap<DayOfWeek, String> activityNew = new EnumMap<DayOfWeek, String>(activityMap);
        activityNew.put(DayOfWeek.FRIDAY, "Lazy Day");
        activityNew.put(DayOfWeek.WEDNESDAY, "Lazy Day");
        System.out.println("New:");
        activityNew.forEach((dayOfWeek, s) -> System.out.println(dayOfWeek));
    }

    static void enumSet() {
        // all the elements of our Color enum
        EnumSet<Color> allColors = EnumSet.allOf(Color.class);
        allColors.forEach(color -> System.out.println(color));
        allColors.remove(Color.BLACK);
        System.out.println("Black is removed");
        allColors.forEach(color -> System.out.println(color));

        // we can use noneOf() to do the opposite and create an empty collection of Color
        EnumSet<Color> noneColors = EnumSet.noneOf(Color.class);
        noneColors.forEach(color -> System.out.println(color));

        // Another way to create a subset of an enum
        EnumSet<Color> rangeOfColors = EnumSet.range(Color.YELLOW, Color.BLUE);
        System.out.println();
        rangeOfColors.forEach(color -> System.out.println(color));

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

enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE
}

enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}