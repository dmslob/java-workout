package com.dmslob.oca.packages.employee;

//import com.dmslob.training.core.oca.packages.aquarium.*;
// or
//import com.dmslob.training.core.oca.packages.aquarium.Water;
//import com.dmslob.training.core.oca.packages.aquarium.jellies.*;
// or

import com.dmslob.oca.packages.aquarium.*;
import com.dmslob.oca.packages.aquarium.jellies.Water;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class WaterFilter {
    Water water;

    public static void main(String[] args) {
        //String message = x > 10 ? "Greater than" : false; // compile error

        boolean x = true, z = true;
        int y = 20;
        x = (z = false) ^ (y != 10);
        System.out.println(x + ", " + y + ", " + z);

        StringBuilder builder = new StringBuilder("test");
        System.out.println(builder.capacity() + " " + builder.length());

        String s = "test";
        // if (builder == s) {} // compile error
        List<String> strings = new ArrayList<>();
        //strings.remove(0); // compile error

        LocalDate date = LocalDate.of(2018, Month.APRIL, 30);
        date.plusDays(2);
        date.plusYears(3);
        System.out.println(date.getYear() + " " + date.getMonth() + " "
                + date.getDayOfMonth());
    }
}
