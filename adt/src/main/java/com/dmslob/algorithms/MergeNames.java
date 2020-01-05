package com.dmslob.algorithms;

import java.util.*;

public class MergeNames {

    static String[] uniqueNames(String[] names1, String[] names2) {
        Set<String> strings = new HashSet<>();
        strings.addAll(Arrays.asList(names1));
        strings.addAll(Arrays.asList(names2));
        String[] names = strings.toArray(new String[strings.size()]);

        return names;
    }

    public static void main(String[] args) {
        String[] names1 = new String[]{"Ava", "Emma", "Olivia"};
        String[] names2 = new String[]{"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia

        search();
    }

    static void search() {
        List<String> hex = Arrays.asList("30", "8", "3A", "FF");
        Collections.sort(hex);
        System.out.println(hex.toString());
        int x = Collections.binarySearch(hex, "8");
        int y = Collections.binarySearch(hex, "3A");
        int z = Collections.binarySearch(hex, "4F");
        System.out.println(x + " " + y + " " + z);
    }
}
