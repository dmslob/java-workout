package com.dmslob.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Snippet {

    public static void main(String[] args) {
        try {
            System.out.println(stringToDate("04-05-2018", "MM-dd-yyyy").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String str = "Hello Dmytro how are you ?";
        String[] strings = splitStringOnArray(str);
        System.out.println(Arrays.toString(strings));

        String[] chrs = {"a", "b", "a"};
        int h = findRecurringCharacters(chrs, "a");
        System.out.println(h);

        String source = "It was cool. But not ideally. It was perfect.";
        String regex = "[^.]*(cool|perfect)[^.]*(\\.|$)";
        String[] byRegex = findByRegex(source, regex);
        System.out.println(Arrays.toString(byRegex));
    }

    public static int findRecurringCharacters(String[] a, String key) {
        int j = 0;
        for (int i = 0; i <= a.length - 1; i++) {
            if (a[i].equals(key)) {
                j++;
            }
        }
        return j;
    }

    public static boolean isContains(String str, String substr) {
        return str.contains(substr);
    }

    public static String[] findByRegex(String source, String regex) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(source);
        List<String> strings = new ArrayList<>();
        while (m.find()) {
            strings.add(m.group());
        }
        return strings.toArray(new String[strings.size()]);
    }

    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static Date stringToDate(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    public static String[] splitStringOnArray(String str) {
        String[] s = str.split("\\s+");
        return s;
    }
}
