package com.tesda8.region8.util.enums;

public enum OperatingUnitType {
    LEYTE("Leyte"),
    SOUTHERN_LEYTE("Southern Leyte"),
    BILIRAN("Biliran"),
    SAMAR("Samar"),
    EASTERN_SAMAR("Eastern Samar"),
    NORTHERN_SAMAR("Northern Samar"),

    TOTAL("Region 8");

    public final String label;

    private OperatingUnitType(String label) {
        this.label = label;
    }
}
