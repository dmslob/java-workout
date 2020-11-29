package com.dmslob.types;

import org.junit.Assert;
import org.junit.Test;

public class PassByValueTest {

    @Test
    public void shouldNotChangeName() {
        PassByValue passByValue = new PassByValue();

        PassByValue.Dog max = new PassByValue().new Dog("Max");
        passByValue.renameDog(max);

        Assert.assertEquals("Max", max.getName());
    }
}
