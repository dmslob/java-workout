package com.dmslob;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class VarTest {

    @Test
    public void should_work_with_loop() {
        Integer.bitCount(1);
        for (var s : Spiciness.values()) {
            log.info("{}", s);
        }
    }

    @Test
    public void should_create_object_with_var() {
        // given
        String userName = "Arny";
        // Object user = new Object() will not compile,
        // because Object does not have getName method
        var user = new Object() {
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        };

        // when
        user.setName(userName);

        // then
        assertThat(user.getClass()).isEqualTo(Object.class);
        assertThat(user.getName()).isEqualTo(userName);
    }

    @Test
    public void should_create_object() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        String userName = "Arny";
        // Object user = new Object() will not work,
        // because Object does not have getName method
        Object user = new Object() {
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        };
        // when
        user.getClass().getMethod("setName", String.class).invoke(user, userName);
        // then
        assertThat(user.getClass().getMethod("getName").invoke(user)).isEqualTo(userName);
    }


    enum Spiciness {
        NOT, MILD, MEDIUM, HOT, FLAMING
    }
}

class Plumbus {
}

class TypeInference {
    void method() {
        // Explicit type:
        String hello1 = "Hello";

        // Type inference:
        var hello2 = "Hello!";

        // Works for user-defined types:
        Plumbus pb1 = new Plumbus();
        var pb2 = new Plumbus();
    }

    // Also works for static methods:
    static void staticMethod() {
        var hello = "Hello!";
        var pb2 = new Plumbus();
    }
}

class NoInference {
    String field1 = "Field initialization";
    // var field2 = "Can't do this";

    // void method() {
    //   var noInitializer; // No inference data
    //   var aNull = null;  // No inference data
    // }

    // var inferReturnType() {
    //   return "Can't infer return type";
    // }
}

class Apple {
}

class GenericTypeInference {
    void previous() {
        List<Apple> apples = new ArrayList<>();
    }

    void now() {
        var apples = new ArrayList<Apple>();
    }

    void pitFall() {
        var apples = new ArrayList<>();
        apples.add(new Apple());
        Object o = apples.get(0); // Comes back as plain Object
        //Apple apple = apples.get(0); // Required type Apple, but provided Object
    }
}