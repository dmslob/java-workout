package com.dmslob.proxy.cglib;

/**
 * Created by Dmytro_Slobodenyuk on 8/10/2018.
 */
public class PersonService {

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public Integer lengthOfName(String name) {
        return name.length();
    }
}
