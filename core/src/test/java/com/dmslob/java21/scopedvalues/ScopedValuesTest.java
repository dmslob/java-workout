package com.dmslob.java21.scopedvalues;

import org.testng.annotations.Test;

public class ScopedValuesTest {

    @Test
    public void should_test_ScopedValue() throws Exception {
        // given
        var userService = new UserService();
        // when
        userService.get();
        // and we get java.util.NoSuchElementException
        userService.test();
    }

    static class UserService {
        private static final ScopedValue<String> USER = ScopedValue.newInstance();

        public void get() throws Exception {
            ScopedValue.runWhere(USER, "Tim Nadella", () -> {
                doSomething();
            });
        }

        public void doSomething() {
            System.out.println("Doing something while accessing scoped value: " + USER.get());
            doSomethingAgain();
        }

        public void doSomethingAgain() {
            System.out.println("Doing something again while accessing scoped value: " + USER.get());
        }

        public void test() {
            System.out.println(USER.get());
        }
    }
}
