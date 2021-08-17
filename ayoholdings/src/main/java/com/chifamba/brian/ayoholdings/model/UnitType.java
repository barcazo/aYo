package com.chifamba.brian.ayoholdings.model;

public enum UnitType {

    /*
    * Unit Type Enum for Unit Conversion
     */
    AREA("Area"),
    LENGTH("Length"),
    TEMPERATURE("Temperature"),
    VOLUME("Volume"),
    WEIGHT("Weight");

    private String name;

    UnitType(String name) {
        this.name = name;
    }
}