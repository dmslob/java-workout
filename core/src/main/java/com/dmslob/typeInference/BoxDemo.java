package com.dmslob.typeInference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BoxDemo {

    private static class Box<U> {
        U u;

        public U get() {
            return u;
        }

        public void set(U u) {
            this.u = u;
        }
    }

    public static <U> void addBox(U u, List<Box<U>> boxes) {
        Box<U> box = new Box<>();
        box.set(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(List<Box<U>> boxes) {
        int counter = 0;
        for (Box<U> box : boxes) {
            U boxContents = box.get();
            System.out.println("Box #" + counter + " contains [" +
                    boxContents.toString() + "]");
            counter++;
        }
    }

    <T> T someMethod(T param) {
        System.out.println(param.getClass());
        return param;
    }

    void doWork() {
        someMethod(new Object());
        someMethod(1);
        this.<Object>someMethod(2);
        Number n = someMethod(3);

        List<String> aList = new ArrayList();
        List<?> bList = new ArrayList<String>();
        List<String> cList = new ArrayList<>();

        Supplier supplier = () -> "Test";
        System.out.println(((Supplier) supplier).get());
    }

    public static void main(String[] args) {
        new BoxDemo().doWork();

        ArrayList<Box<Integer>> listOfIntegerBoxes = new ArrayList<>();
        BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
        BoxDemo.outputBoxes(listOfIntegerBoxes);
    }
}
