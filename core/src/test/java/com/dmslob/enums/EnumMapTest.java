package com.dmslob.enums;

import java.util.EnumMap;

public class EnumMapTest {
    public void test() {
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
}
