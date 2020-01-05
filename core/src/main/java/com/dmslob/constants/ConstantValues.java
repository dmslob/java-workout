package com.dmslob.constants;

import java.lang.reflect.Field;

public class ConstantValues {

    final int fieldInit = 42;   // is constant variable
    final int instanceInit;     // blank final field
    final int constructor;      // blank final field

    {
        instanceInit = 42;
    }

    public ConstantValues() {
        constructor = 42;
    }

    static void set(ConstantValues p, String field) throws Exception {
        Field f = ConstantValues.class.getDeclaredField(field);
        f.setAccessible(true);
        f.setInt(p, 9000);
    }

    public static void main(String... args) throws Exception {
        ConstantValues constantValues = new ConstantValues();

        set(constantValues, "fieldInit");
        set(constantValues, "instanceInit");
        set(constantValues, "constructor");

        System.out.println(constantValues.fieldInit
                + " " + constantValues.instanceInit
                + " " + constantValues.constructor);
    }
}