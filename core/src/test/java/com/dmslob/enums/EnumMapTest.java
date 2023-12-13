package com.dmslob.enums;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumMapTest {

	@Test
	public void testEnumMap() {
		//given
		EnumMap<DayOfWeek, String> activityMap = new EnumMap<>(DayOfWeek.class);
		activityMap.put(DayOfWeek.MONDAY, "Soccer");
		activityMap.put(DayOfWeek.TUESDAY, "Basketball");

		//when
		EnumMap<DayOfWeek, String> activityMapCopy = new EnumMap<>(activityMap);

		// then
		// activityMap.put(Color.BLACK, "Basketball"); // compile error
		assertThat(activityMapCopy.size()).isEqualTo(2);
		assertThat(activityMapCopy.get(DayOfWeek.MONDAY))
				.isEqualTo("Soccer");

		assertThat(activityMapCopy.get(DayOfWeek.MONDAY))
				.isEqualTo("Soccer");
		assertThat(activityMapCopy.get(DayOfWeek.TUESDAY))
				.isEqualTo("Basketball");

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
