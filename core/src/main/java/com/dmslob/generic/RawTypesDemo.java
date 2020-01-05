package com.dmslob.generic;

import java.util.ArrayList;
import java.util.List;

//  using raw types can lead to exceptions at runtime, so don’t use them in new code.
public class RawTypesDemo {

    public static void main(String[] args) {
        String total = "Total # of Work Orders: 18";
        total.split(":");
        int ind = total.indexOf(":");
        String res = total.substring(ind + 1);
        System.out.println(res.length());
        System.out.println(res.trim().length());
        Integer.parseInt(res.trim());

        RawTypesDemo demo = new RawTypesDemo();
        //demo.classCastException();
        //demo.typeSafeGenericCollection();
        //demo.loseTypeSafety();
    }

    public void classCastException() {
        // A raw collection type - don't do this!
        List objects = new ArrayList();
        objects.add("user");
        objects.add(new Integer(35));
        for (Object object : objects) {
            // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
            String expectedString = (String) object;
            System.out.println(expectedString);
        }
    }

    public void typeSafeGenericCollection() {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("user");
        //listOfStrings.add(new Integer(35)); // Compile error
        for (String string : listOfStrings) {
            String expectedString = string;
            System.out.println(expectedString);
        }
    }

    public void loseTypeSafety() {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, new Integer(42));
        // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        String expectedString = strings.get(0); // Compiler-generated cast
        System.out.println(expectedString);
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }
}
