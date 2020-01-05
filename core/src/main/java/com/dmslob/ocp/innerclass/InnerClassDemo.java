package com.dmslob.ocp.innerclass;

import java.lang.reflect.InvocationTargetException;

public class InnerClassDemo {

    public static void main(String[] args) {

        extendedClass();

        try {
            anonymousClass();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void extendedClass() {
        Parent son = new Son();
        System.out.println(son.name);
        System.out.println(son.secondName);
        //System.out.println(son.sonProperty); // Won't compile
    }

    static void anonymousClass() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Parent parent = new Parent() {

            @Override
            public String toString() {
                return "Overriden";
            }

            public void doSomething() {
                System.out.println("Blah");
            }
        };
        System.out.println(parent.toString());
        System.out.println(parent.name);
        System.out.println(parent.secondName);
        //parent.doSomething(); // Won't compile
        parent.getClass().getMethod("doSomething").invoke(parent);

        new Parent() {
            public void foo() {
                System.out.println("Woah");
            }
        }.foo();
    }
}

class Parent {
    protected String name = "Bob";
    public String secondName = "Marley";
}

class Son extends Parent {
    public String name = "Chuck";
    public String secondName = "Marley";
    public String sonProperty = "sonProperty";
}

