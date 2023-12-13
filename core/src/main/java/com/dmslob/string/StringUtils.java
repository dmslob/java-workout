package com.dmslob.string;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static long countCharacter(String[] strings, String key) {
		return Arrays.stream(strings).filter(s -> s.equals(key)).count();
	}

	public static String[] findByRegex(String source, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(source);
		List<String> strings = new ArrayList<>();
		while (m.find()) {
			strings.add(m.group());
		}
		return strings.toArray(new String[0]);
	}

	public static String[] splitStringOnArray(String str) {
		return str.split("\\s+");
	}
}
