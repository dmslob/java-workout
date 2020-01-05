package com.dmslob.conditions;

public class ConditionMain {

    public static void main(String[] args) {

        Object o1 = true ? new Integer(1) : new Double(2.0);

        Object o2;
        if (true) {
            o2 = new Integer(1);
        }
        System.out.println(o1); // 1.0
        System.out.println(o2); // 1

        Object o3 = true ? (Object) new Integer(1) : new Double(2.0);
        System.out.println(o3);

        Integer i = new Integer(1);
        if (i.equals(1)) {
            i = null;
        }
        Double d = 2.0;
        Object o = true ? i : d; // NullPointerException!
        System.out.println(o);
    }
}
