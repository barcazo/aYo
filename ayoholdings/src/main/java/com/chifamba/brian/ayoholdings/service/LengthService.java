package com.chifamba.brian.ayoholdings.service;

import com.chifamba.brian.ayoholdings.conversion.ConverterFactory;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
public class LengthService implements ConverterFactory {

    /**
     * Default distance response entity. This receives the users choice of unit to convert.
     * Mile - Kilometre Conversion
     * Formula taken from https://convert.french-property.co.uk
     * @param kilometre the unit
     * @return the response entity
     */

    @Override
    public double imperialConversion(double kilometre) {
        return Precision.round(kilometre*0.621371, 2);
        }

    @Override
    public double metricalConversion(double mile) {
        return Precision.round(mile*1.60934, 2);
    }
}
