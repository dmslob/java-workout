package com.dmslob.oca.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrapperMain {

    public static void main(String[] args) {
        //castingWrappers();
        //castingBasicTypes();
        //basics();

        //autoboxing();
        //convertingArrayToList();

        equality();
    }

    static void chars() {
        Integer i = 9;
        char c = 'a';
        c += i;
        System.out.println(c);
    }

    static void equality() {
        System.out.println(Float.valueOf(1) != Float.valueOf(1)); //true

        System.out.println(Integer.valueOf(1) == Integer.valueOf(1)); //true
        System.out.println(Integer.valueOf(129) == Integer.valueOf(129)); //false
        System.out.println(new Integer(1) != new Integer(1)); //true
        System.out.println(Integer.valueOf(999) == 999); //true
        System.out.println(Integer.valueOf(1) == Integer.parseInt("1")); //true
        System.out.println(Character.valueOf('\u007f') == 127); // true

        System.out.println(Long.valueOf(1L) == Long.valueOf(1)); //true
        System.out.println((new Boolean("true") == new Boolean("true"))); // false
        System.out.println(new Boolean("true").equals(new Boolean("true"))); // true
        System.out.println(new Boolean("true") == Boolean.parseBoolean("true")); // true.
    }

    static void convertingArrayToList() {
        List<String> list = new ArrayList<>();
        list.add("hawk");
        list.add("robin");

        Object[] objectArray = list.toArray();
        System.out.println(objectArray.length); // 2
        System.out.println(Arrays.toString(objectArray));

        String[] stringArray = list.toArray(new String[0]);
        System.out.println(stringArray.length); // 2
        System.out.println(Arrays.toString(stringArray));

        String[] array = {"hawk", "robin"}; // [hawk, robin]
        List<String> list1 = Arrays.asList(array); // returns fixed size list
        System.out.println(list1.size()); // 2
        list1.set(1, "test"); // [hawk, test]
        //list1.add("el"); //java.lang.UnsupportedOperationException
        //list1.add(0, "rt"); // throws UnsupportedOperation Exception
        array[0] = "new"; // [new, test]
        for (String b : array) System.out.print(b + " "); // new test
        //list1.remove(1); // throws UnsupportedOperation Exception
    }

    static void autoboxing() {
        List<Double> weights = new ArrayList<>();
        weights.add(50.5); // [50.5]
        weights.add(new Double(60)); // [50.5, 60.0]
        weights.remove(50.5); // [60.0]
        double first = weights.get(0); // 60.0

        List<Integer> heights = new ArrayList<>();
        heights.add(null);
        Integer wh = heights.get(0); // OK
        System.out.println(wh);
        //int h = heights.get(0); // NullPointerException

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.remove(1);
        System.out.println(numbers);
        numbers.remove(new Integer(2));
        System.out.println(numbers);
    }

    static void basics() {
        int primitive = Integer.parseInt("123");
        Integer wrapper = Integer.valueOf("123");

        //int bad1 = Integer.parseInt("a"); // throws NumberFormatException
        //Integer bad2 = Integer.valueOf("123.45"); // throws NumberFormatException

        Boolean.parseBoolean("true");
        Boolean.valueOf("TRUE");

        Byte.parseByte("1");
        //Byte.parseByte("150"); // throws NumberFormatException
        Byte.valueOf("2");

        Short.parseShort("1");
        Short.valueOf("2");

        Integer.parseInt("1");
        Integer.valueOf("2");

        Long.parseLong("1");
        Long.valueOf("2");

        Float.parseFloat("1");
        Float.valueOf("2.2");

        Double.parseDouble("1");
        Double.valueOf("2.2");

        System.out.println(new Boolean("tRue"));
        System.out.println(new Boolean(null));
        System.out.println(new Boolean(""));

        Boolean b = Boolean.parseBoolean("true"); // OK but wrapping
        boolean bb = Boolean.parseBoolean("true"); // return boolean not Boolean
        int x = Integer.parseInt("12"); // return int not Integer
        Boolean bo = Boolean.valueOf("true"); // return Boolean object
    }

    static void castingWrappers() {
        Integer integerObject = 1;
        Integer integerObject2 = new Integer(2);
        int intVar = 1;
        Byte byteObject = 1;
        Long longObject = 1L;

        System.out.println(integerObject == integerObject2); // false
        System.out.println(integerObject == intVar); // true
        //System.out.println(integerObject == byteObject); // compile error
        System.out.println(integerObject.equals(integerObject2)); // true
        System.out.println(integerObject.equals(longObject)); // false
        System.out.println(integerObject.equals(byteObject)); // false

        //String stringObject = (String) m1(); //java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.String

        Object object = new Integer(107);
        //int k = (Integer) object.intValue()/9;
        int k = ((Integer) object).intValue() / 9;
    }

    static Object m1() {
        return new Object();
    }

    static void castingBasicTypes() {
        Short shortObject = 9;
        Integer integerObject = 9;
        Boolean bool = false;
        char charVal = 'a';
        String stringObject = "123";

        integerObject = (int) shortObject.shortValue();
        stringObject += bool;
        bool = !bool;
        charVal *= integerObject;

        System.out.println(charVal);
    }
}
