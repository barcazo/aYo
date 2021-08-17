package com.chifamba.brian.ayoholdings.service;

import com.chifamba.brian.ayoholdings.conversion.ConverterFactory;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
public class WeightService implements ConverterFactory {

    /**
     * Default weight response entity. This receives the users choice of unit to convert.
     * Pound - Kilogram Conversion
     * Formula taken from https://convert.french-property.co.uk
     * @param kilogram the unit
     * @return the response entity
     */

    @Override
    public double imperialConversion(double kilogram) {
        return Precision.round(kilogram*2.20462, 2);
        }

    @Override
    public double metricalConversion(double pound) {
        return Precision.round(pound*0.453592, 2);
    }
}
