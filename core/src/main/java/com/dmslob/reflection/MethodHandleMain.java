package com.dmslob.reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

public class MethodHandleMain {

    public static void main(String[] args) throws Throwable {
        //MethodHandleMain handleMain = new MethodHandleMain();
        //handleMain.getMethod();

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // mt is (char,char)String
        MethodType mt = MethodType.methodType(void.class, Object.class);
        MethodHandle mh = lookup.findVirtual(MethodHandleMain.class, "print", mt);
        mh = mh.bindTo(new MethodHandleMain());

        mh.invoke("Hello World");
    }

    public void print(Object s) {
        System.out.println(s);
    }

    void getMethod() throws Throwable {

        MethodHandles.Lookup lookup = MethodHandles.lookup();

        MethodType mt = MethodType.methodType(String.class, char.class, char.class);
        MethodHandle replaceMH = lookup.findVirtual(String.class, "replace", mt);

        String output = (String) replaceMH.invoke("Jovo", Character.valueOf('o'), 'a');
        System.out.println(output);

        MethodType methodType = MethodType.methodType(List.class, Object[].class);
        MethodHandle asList = lookup.findStatic(Arrays.class, "asList", methodType);

        List<Integer> list = (List<Integer>) asList.invokeWithArguments(1, 2);
        System.out.println(list.toString());
    }
}