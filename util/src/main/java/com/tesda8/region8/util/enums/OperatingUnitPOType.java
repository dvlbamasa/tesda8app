package com.tesda8.region8.util.enums;

public enum OperatingUnitPOType {

    LEYTE_PO("Leyte PO", "HIGHLIGHTED"),
    BILIRAN_PO("Biliran PO", "NON-HIGHLIGHTED"),
    SOUTHERN_LEYTE_PO("Southern Leyte PO", "HIGHLIGHTED"),
    SAMAR_PO("Samar PO", "NON-HIGHLIGHTED"),
    EASTERN_SAMAR_PO("Eastern Samar PO", "HIGHLIGHTED"),
    NORTHERN_SAMAR_PO("Northern Samar PO", "NON-HIGHLIGHTED"),
    TESDA_RO("TESDA RO", "HIGHLIGHTED"),

    TOTAL("Region 8", "NON-HIGHLIGHTED");

    public final String label;
    public final String tableType;


    private OperatingUnitPOType(String label, String tableType) {
        this.label = label;
        this.tableType = tableType;
    }
}
