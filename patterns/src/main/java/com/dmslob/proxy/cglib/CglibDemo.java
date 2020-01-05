package com.dmslob.proxy.cglib;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.Mixin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CglibDemo {

    public static void main(String[] args) throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        returningTheSameValue();
        //returningValueDependingOnMethodSignature();
        //beanCreator();
        //creatingMixin();
    }

    static void returningTheSameValue() throws InterruptedException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "Hello Tom!");
        PersonService proxy = (PersonService) enhancer.create();

        String res = proxy.sayHello(null);
        System.out.println(res);
    }

    static void returningValueDependingOnMethodSignature() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello Tom!";
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });

        PersonService proxy = (PersonService) enhancer.create();
        System.out.println(proxy.sayHello(null));

        int lengthOfName = proxy.lengthOfName("Mary");
        System.out.println("" + lengthOfName);
    }

    static void beanCreator() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        BeanGenerator beanGenerator = new BeanGenerator();

        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "some string value set by a cglib");

        Method getter = myBean.getClass().getMethod("getName");
        System.out.println(getter.invoke(myBean));
        //assertEquals("some string value set by a cglib", getter.invoke(myBean));
    }

    static void creatingMixin() {
        Mixin mixin = Mixin.create(
                new Class[]{InterfaceOne.class, InterfaceTwo.class, MixinInterface.class},
                new Object[]{new OneImpl(), new TwoImpl()}
        );
        MixinInterface mixinDelegate = (MixinInterface) mixin;

        System.out.println("first behaviour: " + mixinDelegate.first());
        System.out.println("second behaviour: " + mixinDelegate.second());
    }
}
