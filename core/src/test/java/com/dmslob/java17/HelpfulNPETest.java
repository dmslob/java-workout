package com.dmslob.java17;

import org.testng.annotations.Test;

import java.awt.*;
import java.util.HashMap;

public class HelpfulNPETest {

    @Test
    public void should_throw_NPE() {
        // given
        var grapes = new HashMap<String, Grape>();
        grapes.put("grape1", new Grape(Color.BLUE, 2));
        grapes.put("grape2", new Grape(Color.white, 4));
        grapes.put("grape3", null);

        // when
        var color = ((Grape) grapes.get("grape3")).color();
    }

    record Grape(Color color, int numberOfPits) {
    }
}
