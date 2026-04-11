package com.dmslob.patterns.creational;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {

    static class BillPughSingleton {
        private BillPughSingleton() {
            System.out.println("BillPughSingleton is initialized");
        }

        private static class SingletonHolder {
            static {
                System.out.println("SingletonHolder is initialized");
            }

            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }

        public static BillPughSingleton getInstance() {
            System.out.println("getInstance is called");
            return SingletonHolder.INSTANCE;
        }

        public String greeting() {
            return "Hello from the Singleton";
        }
    }

    @Test
    public void should_test_BillPughSingleton() {
        // given
        var instance1 = BillPughSingleton.getInstance();
        // when
        var greeting = instance1.greeting();
        // then
        assertThat(greeting).isEqualTo("Hello from the Singleton");
        // given
        var instance2 = BillPughSingleton.getInstance();
        // then
        assertThat(instance1 == instance2).isTrue();
    }

    /**
     * Turns out to not work reliably because of issues with optimizing compilers
     * and shared memory multiprocessors.
     */
    static class DoubleCheckedSingleton {
        private DoubleCheckedSingleton() {
        }

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
