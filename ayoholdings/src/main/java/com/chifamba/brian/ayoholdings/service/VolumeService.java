package com.chifamba.brian.ayoholdings.service;

import com.chifamba.brian.ayoholdings.conversion.ConverterFactory;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
public class VolumeService implements ConverterFactory {

    /**
     * Default volume response entity. This receives the users choice of unit to convert.
     * Galon - Litre Conversion
     * Formula taken from https://convert.french-property.co.uk
     * @param litre the unit
     * @return the response entity
     */

    @Override
    public double imperialConversion(double litre) {
        return Precision.round(litre*0.219969, 2);
        }

    @Override
    public double metricalConversion(double galon) {
        return Precision.round(galon*4.54609, 2);
    }
}
