package com.dmslob;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

public class CrazyTest {

    @Test
    public void should_work_somehow() {
        Continuation c = new Mondo();
        c.run(c);
    }
}

interface Continuation {
    void run(Continuation c);
}

@Slf4j
class Mondo implements Continuation {
    @Override
    public void run(Continuation c) {
        Continuation let = new Let();
        let.run(c);
    }

    class Let implements Continuation {
        Continuation k;

        @Override
        public void run(Continuation c) {
            k = c;
            log.info("1");
            k.run(new C2());
        }

        class C2 implements Continuation {
            @Override
            public void run(Continuation c) {
                log.info("2");
                k.run(new C3());
            }
        }

        class C3 implements Continuation {
            @Override
            public void run(Continuation c) {
                log.info("3");
            }
        }
    }
}
