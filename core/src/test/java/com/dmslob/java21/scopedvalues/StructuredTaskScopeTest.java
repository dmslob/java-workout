package com.dmslob.java21.scopedvalues;

import org.testng.annotations.Test;

import java.util.concurrent.StructuredTaskScope;

public class StructuredTaskScopeTest {
    public static ScopedValue<String> location = ScopedValue.newInstance();

    @Test
    public void should_test_StructuredTaskScope() {
        // given
        ScopedValue.runWhere(location, "New York", StructuredTaskScopeTest::getWeather);
        // when
        getWeather();
        // then
    }

    /**
    * let’s say to increase the performance & reliability of our application
     * we decided to use all the three WeatherService implementations
     * and return as soon as any one of them returns the weather
     */
    public static void getWeather() {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            scope.fork(() -> new SunnyWeatherService().getWeather());
            scope.fork(() -> new CloudyWeatherService().getWeather());
            scope.fork(() -> new RainyWeatherService().getWeather());
            scope.join();
            System.out.println(scope.result(e -> new RuntimeException(e)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

interface WeatherService {
    String getWeather();
}

class SunnyWeatherService implements WeatherService {
    @Override
    public String getWeather() {
        if (StructuredTaskScopeTest.location.isBound()) { //Check whether the value is available
            return "Weather for " + StructuredTaskScopeTest.location.get() + " Sunny";
        }
        throw new RuntimeException("Location not specified");
    }
}

class CloudyWeatherService implements WeatherService {
    @Override
    public String getWeather() {
        if (StructuredTaskScopeTest.location.isBound()) { //Check whether the value is available
            return "Weather for " + StructuredTaskScopeTest.location.get() + " Cloudy";
        }
        throw new RuntimeException("Location not specified");
    }
}

class RainyWeatherService implements WeatherService {
    @Override
    public String getWeather() {
        if (StructuredTaskScopeTest.location.isBound()) { //Check whether the value is available
            return "Weather for " + StructuredTaskScopeTest.location.get() + " Rainy";
        }
        throw new RuntimeException("Location not specified");
    }
}
