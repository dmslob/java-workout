package com.dmslob.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public enum Planet {
	VENUS(4000) {
		public int getRadius() {
			return this.getSize() / 2;
		}
	},
	MERCURY(2000) {
		public int getRadius() {
			return this.getSize() / 2;
		}
	};

	static {
		log.info("Planet's static block");
	}

	private final int size;

	abstract int getRadius();

	Planet(int size) {
		this.size = size;
		System.out.printf("Planet size: %d%n", size);
	}

	public int getSize() {
		return size;
	}

	public static Planet valueOfPlanet(String label) {
		final Number[] arr = new Number[10];
		arr[0] = Long.valueOf("23");
		arr[1] = Short.valueOf("34");

		for (Planet planet : values()) {
			if (planet.name().equals(label)) {
				return planet;
			}
		}
		return null;
	}
}
