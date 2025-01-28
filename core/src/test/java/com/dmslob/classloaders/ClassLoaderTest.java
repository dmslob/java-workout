package com.dmslob.classloaders;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class ClassLoaderTest {

    @Test
    public void should_load_MyRunnable_class() throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // given
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                if (name.equals("MyRunnable")) {
                    try (InputStream inputStream = MyRunnable.class.getResourceAsStream("MyRunnable.class")) {
                        assert inputStream != null;
                        byte[] classData = inputStream.readAllBytes();
                        return defineClass("MyRunnable", classData, 0, classData.length);
                    } catch (IOException e) {
                        throw new ClassNotFoundException();
                    }
                }
                return super.loadClass(name);
            }
        };
        // when
        Runnable origin = new MyRunnable();
        origin.run();
        Class<?> loadedClass = loader.loadClass("com.dmslob.classloaders.MyRunnable");
        Runnable loaded = loadedClass.asSubclass(Runnable.class).getConstructor().newInstance();
        loaded.run();
        // then
        Assert.assertEquals(origin.getClass(), loaded.getClass());
        Assert.assertEquals(origin.getClass().getClassLoader(), loaded.getClass().getClassLoader());

        Testable testable = () -> {
            System.out.println("fdf");
        };
        testable.test();
    }
}

interface Testable {
    void run();
    default void test() {
        run();
        System.out.println("Test");
    }
}