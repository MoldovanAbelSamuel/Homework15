package org.fasttrack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TemperatureConverterTest {


    @Test
    public void testFahrenheitToCelsius(){
        double temperature = 100;
        double expectedValue = 37.77777777777778;
        double result = TemperatureConverter.fahrenheitToCelsius(temperature);
        Assertions.assertEquals(expectedValue, result);
        temperature = 0;
        expectedValue = -17.77777777777778;
        result = TemperatureConverter.fahrenheitToCelsius(temperature);
        Assertions.assertEquals(expectedValue, result);
        temperature = 50;
        expectedValue = 10;
        result = TemperatureConverter.fahrenheitToCelsius(temperature);
        Assertions.assertEquals(expectedValue, result);
    }

    @Test
    public void testCelsiusToFahrenheit(){
        double temperature = 37.77777777777778;
        double expectedValue = 100;
        double result = TemperatureConverter.celsiusToFahrenheit(temperature);
        Assertions.assertEquals(expectedValue, result);
        temperature = -17.77777777777778;
        expectedValue = 0;
        result = TemperatureConverter.celsiusToFahrenheit(temperature);
        Assertions.assertEquals(expectedValue, result);
        temperature = 10;
        expectedValue = 50;
        result = TemperatureConverter.celsiusToFahrenheit(temperature);
        Assertions.assertEquals(expectedValue, result);
    }
}
