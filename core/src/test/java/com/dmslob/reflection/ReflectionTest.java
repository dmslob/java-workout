package com.dmslob.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class ReflectionTest {

    private static final Logger LOGGER = LogManager.getLogger(ReflectionTest.class);

    private List<String> strings;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface ExampleAnnotation {
        // Some attributes here
    }

    @ExampleAnnotation
    public class ExampleClass {
        // Some getter and setters here
    }

    @Test
    public void annotationTest() {
        final ExampleAnnotation annotation = ExampleClass.class.getAnnotation(ExampleAnnotation.class);
        if (annotation != null) {
            // Some implementation here
            LOGGER.info(annotation);
        }
    }

    @Test
    public void newInstanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
        final Constructor<String> constructor = String.class.getConstructor(String.class);
        final String str = constructor.newInstance("New string");
        final Method method = String.class.getMethod("length");
        final int length = (int) method.invoke(str);

        Assert.assertTrue(length == 10);
    }

    @Test
    public void parameterizedTypeTest() throws NoSuchFieldException, SecurityException {
        final Type type = ReflectionTest.class.getDeclaredField("strings").getGenericType();
        if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType) type;
            for (final Type typeArgument : parameterizedType.getActualTypeArguments()) {
                LOGGER.info(typeArgument);
            }
        }
    }

    @Test
    public void getMethodParametersTest() throws NoSuchMethodException, SecurityException {
        Class[] classes = new Class[1];
        classes[0] = String.class;
        final Method method = Operation.class.getDeclaredMethod("setPriorityScore", classes);
        Arrays.stream(method.getParameters()).forEach(p -> LOGGER.info(p.getName() + ":" + p.getType()));
    }

    @Test
    public void shutDownHookTest() {
        ShutDownHook shutDownHook = new ShutDownHook();
        shutDownHook.shutDown();
    }

    @Test
    public void setValueTest() throws Throwable {
        Method setMethod = Operation.class.getMethod("setPriorityScore", String.class);
        Operation operationsInstance = new Operation();
        setMethod.invoke(operationsInstance, "HIGH");

        LOGGER.info(operationsInstance.getPriorityScore());
    }

    @Test
    public void methodHandleTest() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        MethodType methodType = MethodType.methodType(String.class, char.class, char.class);
        MethodHandle replaceHandle = lookup.findVirtual(String.class, "replace", methodType);

        String output = (String) replaceHandle.invoke("Jovo", Character.valueOf('o'), 'a');
        LOGGER.info(output);
    }
}
