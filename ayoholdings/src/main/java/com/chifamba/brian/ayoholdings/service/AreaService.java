package com.chifamba.brian.ayoholdings.service;

import com.chifamba.brian.ayoholdings.conversion.ConverterFactory;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
public class AreaService implements ConverterFactory {

    /**
     * Default weight response entity. This receives the users choice of unit to convert.
     * Acre - Square Metre Conversion
     * Formula taken from https://convert.french-property.co.uk
     * @param squareMetreArea the unit
     * @return the response entity
     */


    @Override
    public double imperialConversion(double squareMetreArea) {
        return Precision.round(squareMetreArea*0.000247105, 2);
        }

    @Override
    public double metricalConversion(double acreArea) {
        return Precision.round(acreArea*4046.86, 2);
    }
}
