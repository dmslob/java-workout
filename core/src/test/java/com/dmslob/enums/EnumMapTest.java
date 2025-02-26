package com.dmslob.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class EnumMapTest {

	enum DayOfWeek {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}

	@Test
	public void test_enum_map() {
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
		assertThat(activityMapCopy.get(DayOfWeek.FRIDAY)).isNull();

		System.out.println("Values:");
		activityMapCopy.values().forEach(System.out::println);

		System.out.println("KeySet:");
		activityMapCopy.keySet().forEach(System.out::println);

		System.out.println("EntrySet:");
		activityMapCopy.forEach((key, value) -> System.out.println(
				key + " : " + value));

		EnumMap<DayOfWeek, String> activityNew = new EnumMap<>(activityMap);
		activityNew.put(DayOfWeek.FRIDAY, "Lazy Day");
		activityNew.put(DayOfWeek.WEDNESDAY, "Lazy Day");
		System.out.println("New:");
		activityNew.forEach((dayOfWeek, s) -> System.out.println(dayOfWeek));
	}
}
