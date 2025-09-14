package com.dmslob.patterns.creational;

import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FactoryMethodTest {

    // Product Interface
    interface Transport {
        String deliver();
    }

    // Concrete Products
    static class Truck implements Transport {
        @Override
        public String deliver() {
            return "Delivering by truck.";
        }
    }

    static class Ship implements Transport {
        @Override
        public String deliver() {
            return "Delivering by ship.";
        }
    }

    // Creator Abstract Class
    static abstract class Logistics {
        public String planDelivery() {
            Transport transport = createTransport();
            return transport.deliver();
        }
        // Factory Method
        protected abstract Transport createTransport();
    }

    // Concrete Creators
    static class RoadLogistics extends Logistics {
        @Override
        protected Transport createTransport() {
            return new Truck();
        }
    }

    static class SeaLogistics extends Logistics {
        @Override
        protected Transport createTransport() {
            return new Ship();
        }
    }

    @Test
    public void should_test_factory_method() {
        // given
        Logistics roadLogistics = new RoadLogistics();
        // when
        var result = roadLogistics.planDelivery();
        // then
        assertThat(result).isEqualTo("Delivering by truck.");
        // given
        Logistics seaLogistics = new SeaLogistics();
        // when
        result = seaLogistics.planDelivery();
        // then
        assertThat(result).isEqualTo("Delivering by ship.");
    }
}
