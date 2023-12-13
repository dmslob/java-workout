package com.dmslob.proxy;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

public class DynamicDemo {

    public static void main(String args[]) {

        // Create the target instance
        ServiceOne serviceOne = new ServiceOneBean();

        // Create the proxy
        ServiceOne proxy = (ServiceOne) Proxy.newProxyInstance(
                ServiceOne.class.getClassLoader(),
                serviceOne.getClass().getInterfaces(),
                new LogExecutionTimeProxy(serviceOne));

        // Invoke the target instance method through the proxy
        String result = proxy.sayHello();
        System.out.println("Result: " + result);

        new InterfaceMethodProxyDemo().test();
        new InterfaceDefaultMethodProxyDemo().test();
        new DefaultMethodOnNonPrivateAccessibleInterface().test();
    }
}

class InterfaceMethodProxyDemo {

    interface Duck {
        void quack();
    }

    public void test() {
        Duck duck = (Duck) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{Duck.class},
                (proxy, method, args) -> {
                    System.out.println("Quack");
                    return null;
                }
        );

        duck.quack();
    }
}

class InterfaceDefaultMethodProxyDemo {

    interface Duck {
        default void quack() {
            System.out.println("Quack");
        }
    }

    public void test() {

        Duck duck = (Duck) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{Duck.class},
                (proxy, method, args) -> {
                    MethodHandles
                            .lookup()
                            .in(Duck.class)
                            .unreflectSpecial(method, Duck.class)
                            .bindTo(proxy)
                            .invokeWithArguments();
                    return null;
                }
        );

        duck.quack();
    }
}

interface Duck {
    default void quack() {
        System.out.println("Quack");
    }
}

class DefaultMethodOnNonPrivateAccessibleInterface {

    public void test() {
        Duck duck = (Duck) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{Duck.class},
                (proxy, method, args) -> {
                    Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                            .getDeclaredConstructor(Class.class);
                    constructor.setAccessible(true);
                    constructor.newInstance(Duck.class)
                            .in(Duck.class)
                            .unreflectSpecial(method, Duck.class)
                            .bindTo(proxy)
                            .invokeWithArguments();
                    return null;
                }
        );

        duck.quack();
    }
}
