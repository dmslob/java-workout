package com.dmslob.init;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ObjectInitTest {

    @Test
    public void should_not_create_an_object_when_exception() {
        Invoice invoice = null;
        try {
            invoice = new Invoice();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Assert.assertNull(invoice);
    }

    @Test
    public void should_init_statics() {
        // when
        var beltSize = Elvis.INSTANCE.beltSize();
        // then
        assertThat(beltSize).isEqualTo(-1930);
    }

    private static class Elvis {
        public static final Elvis INSTANCE = new Elvis();
        private static final int CURRENT_YEAR =
                Calendar.getInstance().get(Calendar.YEAR);

        private final int beltSize;

        private Elvis() {
            beltSize = CURRENT_YEAR - 1930;
        }

        public int beltSize() {
            return beltSize;
        }
    }

    @Test
    public void should_init_InitTest() throws NoSuchMethodException {
        System.out.println("Class: " + InitTest.class);
        InitTest[] initTests = new InitTest[10];

        System.out.println("array is initialized");
        System.out.println(InitTest.class.getDeclaredConstructor());
        System.out.println(new InitTest());
    }

    @Test
    public void should_not_init_initTestBroken() {
        // given
        Throwable error = null;
        // when
        try {
            new InitTestBroken();
        } catch (Throwable throwable) {
            error = throwable;
        }
        // then
        Assert.assertNotNull(error);
        Assert.assertEquals(error.getClass(), ExceptionInInitializerError.class);
        // when
        try {
            new InitTestBroken();
        } catch (Throwable throwable) {
            error = throwable;
        }
        // then
        Assert.assertEquals(error.getClass(), NoClassDefFoundError.class);
    }
}

class InitTest {
    static {
        System.out.println("InitTest is initialized");
    }
}

class InitTestBroken {
    static {
        System.out.println("Initializing...");
        if (true) throw new RuntimeException("Oops");
    }
}
