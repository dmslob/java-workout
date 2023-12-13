package com.dmslob.enums;

import org.junit.Assert;
import org.junit.Test;

public class EnumTest {

	@Test
	public void shouldBeEqualsWhenComparingByValueOf() {
		Planet expectedPlanet = Planet.MERCURY;
		Planet actualPlanet = Planet.valueOf("MERCURY");

		Assert.assertEquals(expectedPlanet, actualPlanet);
	}

	@Test
	public void shouldGetEnumByValue() {
		Planet expectedPlanet = Planet.MERCURY;
		Planet actualPlanet = Planet.valueOfPlanet("MERCURY");

		Assert.assertEquals(expectedPlanet, actualPlanet);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenNoEnumValue() {
		Planet expectedPlanet = Planet.MERCURY;
		Planet actualPlanet = Planet.valueOf("EARTH");

		Assert.assertEquals(expectedPlanet, actualPlanet);
	}

	@Test
	public void foo() {
		System.out.println("Good mood, laughing " + Mood.GOOD + " " + Emotion.LAUGHING);
	}

	@Test
	public void testUser() {
		var user = new User();
	}
}

interface Emoticonable {
	String getIcon();
}

enum Mood implements Emoticonable {
	GOOD {
		public String getIcon() {
			return ";-)";
		}
	},
	BAD {
		public String getIcon() {
			return ";-(";
		}
	};

	public String toString() {
		return getIcon();
	}
}

enum Emotion implements Emoticonable {
	LAUGHING {
		public String toString() {
			return getIcon();
		}
	},
	CRYING {
		public String getIcon() {
			return ";'-(";
		}
	};

	public String getIcon() {
		return ";-|";
	}
}

enum State {
	NEW, VALIDATED;

	{
		System.out.println("State init");
	}

	State() {
		System.out.println("State constructor");
	}

	static {
		System.out.println("State static");
	}
}

class User {
	State state;

	public User() {
		System.out.println("User constructor");
	}

	{
		state = State.VALIDATED;
		System.out.println("User init");
	}
}
