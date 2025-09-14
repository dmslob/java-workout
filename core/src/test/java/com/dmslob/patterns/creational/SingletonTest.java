package com.dmslob.patterns.creational;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {

    static class LazySingleton {
        // Private constructor to prevent external instantiation
        private LazySingleton() {
            // Optional: Add initialization logic here
        }
        // Static inner class to hold the Singleton instance
        private static class SingletonHolder {
            // The instance is created only when SingletonHolder is initialized
            private static final LazySingleton INSTANCE = new LazySingleton();
        }

        public static LazySingleton getInstance() {
            return SingletonHolder.INSTANCE;
        }

        public String greeting() {
            return "Hello from the Lazy Singleton";
        }
    }

    @Test
    public void should_test_singleton() {
        // given
        var instance1 = LazySingleton.getInstance();
        // when
        var greeting = instance1.greeting();
        // then
        assertThat(greeting).isEqualTo("Hello from the Lazy Singleton");
        // given
        var instance2 = LazySingleton.getInstance();
        // then
        assertThat(instance1 == instance2).isTrue();
    }

    static class DoubleCheckedSingleton {
        private DoubleCheckedSingleton() {}
        private static volatile DoubleCheckedSingleton instance;

        public static DoubleCheckedSingleton getInstance() {
            if (instance == null) {
                synchronized (DoubleCheckedSingleton.class) {
                    if (instance == null) {
                        instance = new DoubleCheckedSingleton();
                    }
                }
            }
            return instance;
        }
    }
}
