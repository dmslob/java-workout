package com.dmslob.oop.inheritance.two;

import com.dmslob.oop.inheritance.one.DefaultPackage;

public class DefaultPackageDemo {
    public static void main(String[] args) {
        DefaultPackage defaultPackage = new DefaultPackage(100, 12344L);
        //System.out.println(defaultPackage.defaultField);
        //System.out.println(defaultPackage.protectedField);

        SubDefaultPackageDemo subDefaultPackageDemo = new SubDefaultPackageDemo(300, 122343L);
        System.out.println(subDefaultPackageDemo);
    }
}
