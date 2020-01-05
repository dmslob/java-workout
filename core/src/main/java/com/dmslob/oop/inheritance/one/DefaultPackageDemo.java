package com.dmslob.oop.inheritance.one;

public class DefaultPackageDemo {
    public static void main(String[] args) {
        DefaultPackage defaultPackage = new DefaultPackage(100, 12344L);
        System.out.println(defaultPackage.defaultField);
        System.out.println(defaultPackage.protectedField);
    }
}
