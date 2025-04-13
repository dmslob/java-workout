package com.dmslob.generics;

import org.testng.annotations.Test;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionTest {

    void printClass(Class<?> cl) {
        System.out.print(cl);
        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);
        Type type = cl.getGenericSuperclass();
        if (type != null) {
            System.out.print(" extends ");
            printType(type, false);
        }
        printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);
        System.out.println();
    }

    void printMethod(Method method) {
        String name = method.getName();
        System.out.print(Modifier.toString(method.getModifiers()));
        System.out.print(" ");
        printTypes(method.getTypeParameters(), "<", ", ", "> ", true);

        printType(method.getGenericReturnType(), false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(method.getGenericParameterTypes(), "", ", ", "", false);
        System.out.println(")");
    }

    void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[]{Object.class})) return;
        if (types.length > 0) System.out.print(pre);
        for (int i = 0; i < types.length; i++) {
            if (i > 0) System.out.print(sep);
            printType(types[i], isDefinition);
        }
        if (types.length > 0) System.out.print(suf);
    }

    void printType(Type type, boolean isDefinition) {
        if (type instanceof Class<?> aClass) {
            System.out.print(aClass.getName());
        } else if (type instanceof TypeVariable<?> typeVariable) {
            System.out.print(typeVariable.getName());
            if (isDefinition) {
                printTypes(typeVariable.getBounds(), " extends ", " & ", "", false);
            }
        } else if (type instanceof WildcardType wildcardType) {
            System.out.print("?");
            printTypes(wildcardType.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(wildcardType.getLowerBounds(), " super ", " & ", "", false);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);
        } else if (type instanceof GenericArrayType genericArrayType) {
            System.out.print("");
            printType(genericArrayType.getGenericComponentType(), isDefinition);
            System.out.print("[]");
        }
    }

    @Test
    public void should_print_generic_info_for_class_and_public_methods() {
        try {
            Class<?> cl = Class.forName("com.dmslob.generics.Pair");
            printClass(cl);
            for (Method m : cl.getDeclaredMethods())
                printMethod(m);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
