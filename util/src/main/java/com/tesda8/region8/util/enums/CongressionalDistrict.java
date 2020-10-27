package com.tesda8.region8.util.enums;

public enum CongressionalDistrict {
    FIRST("1st"),
    SECOND("2nd"),
    THIRD("3rd"),
    FOURTH("4th"),
    FIFTH("5th"),
    LONE("Lone");

    public final String label;

    private CongressionalDistrict(String label) {
        this.label = label;
    }
}
