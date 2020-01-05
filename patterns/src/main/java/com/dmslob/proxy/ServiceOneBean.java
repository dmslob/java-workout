package com.dmslob.proxy;

/**
 * Created by Dmytro_Slobodenyuk on 8/10/2018.
 */
public class ServiceOneBean implements ServiceOne {

    public String sayHello() {
        System.out.println("Executing method sayHello");
        return "Hello";
    }
}
