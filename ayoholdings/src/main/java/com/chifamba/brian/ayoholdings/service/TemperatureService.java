package com.chifamba.brian.ayoholdings.service;

import com.chifamba.brian.ayoholdings.conversion.ConverterFactory;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService implements ConverterFactory {

    /**
     * Default temperature response entity. This receives the users choice of unit to convert.
     * Fahreinheit - Celcius Conversion
     * Formula taken from https://convert.french-property.co.uk
     * @param celcius the unit
     * @return the response entity
     */

    @Override
    public double imperialConversion(double celcius) {
        return Precision.round((celcius*1.8)+32, 2);
        }

    @Override
    public double metricalConversion(double fahrenheit) {
        return Precision.round((fahrenheit - 32) / 1.8, 2);
    }
}
