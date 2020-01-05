package com.dmslob.oop.inheritance.two;

import com.dmslob.oop.inheritance.one.DefaultPackage;

public class SubDefaultPackageDemo extends DefaultPackage {
    public SubDefaultPackageDemo(int defaultField, long protectedField) {
        super(defaultField, protectedField);
    }

    @Override
    public void testException() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        SubDefaultPackageDemo subDefaultPackageDemo = new SubDefaultPackageDemo(23, 45L);
        System.out.println(subDefaultPackageDemo.protectedField);
        subDefaultPackageDemo.testException();
    }
}