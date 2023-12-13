package com.dmslob.algorithms;

import java.util.LinkedList;
import java.util.List;

public class BracketsValidator {

	private static final char OPEN_BRACKET = '(';
	private static final char CLOSE_BRACKET = ')';

	public List<Integer> validate(String expression) {
		var chars = new LinkedList<Character>();
		var noValidPositions = new LinkedList<Integer>();

		for (int i = 0; i < expression.length(); i++) {
			char currentChar = expression.charAt(i);
			switch (currentChar) {
				case OPEN_BRACKET:
					chars.addFirst(currentChar);
					noValidPositions.addFirst(i);
					break;
				case CLOSE_BRACKET:
					if (!chars.isEmpty()) {
						char chClosed = chars.removeFirst();
						noValidPositions.removeFirst();
						if (OPEN_BRACKET != chClosed) {
							noValidPositions.addFirst(i);
						}
					} else {
						noValidPositions.addFirst(i);
					}
					break;
				default:
					break;
			}
		}
		return noValidPositions;
	}
}
