package com.chifamba.brian.ayoholdings.model;

public enum UnitSystem {

    /*
     * Conversion Systems
     */
    IMPERIAL("Imperial"),
    METRIC("Metric");

    private String name;

    UnitSystem(String name) {
        this.name = name;
    }

}
