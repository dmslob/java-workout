package com.dmslob.oca;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Beta extends Baap {

    public int h = 44;

    public int getH() {
        System.out.println("Beta " + h);
        return h;
    }

    public boolean checkList(List list, Predicate<List> p) {
        return p.test(list);
    }

    public static void main(String[] args) {
        Baap b = new Beta();
        System.out.println(b.h + " " + b.getH());
        ((Beta) b).checkList(new ArrayList(), a1 -> a1.isEmpty());
        ((Beta) b).checkList(new ArrayList(), (List a1) -> a1.add("a1"));
        // ((Beta) b).checkList(new ArrayList(), (ArrayList a1) -> a1.add("a1")); // incompatible parameter


        Beta bb = (Beta) b;
        System.out.println(bb.h + " " + bb.getH());

        int sw = 2;
        switch (sw) {
            case 0:
                boolean n = false;
                break;
            case 1:
                n = true;
                break;
        }

        String[] sA = {new String("")};
        String h = "";
        h.length();
    }
}

class Baap {

    public int h = 4;

    public int getH() {
        System.out.println("Baap " + h);
        return h;
    }
}