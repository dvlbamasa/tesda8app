package com.tesda8.region8.util.enums;

public enum OperatingUnitPOType {

    LEYTE_PO("Leyte PO"),
    BILIRAN_PO("Biliran PO"),
    SOUTHERN_LEYTE_PO("Southern Leyte PO"),
    SAMAR_PO("Samar PO"),
    EASTERN_SAMAR_PO("Eastern Samar PO"),
    NORTHERN_SAMAR_PO("Northern Samar PO"),
    TESDA_RO("TESDA RO"),

    TOTAL("Region 8");

    public final String label;

    private OperatingUnitPOType(String label) {
        this.label = label;
    }
}
