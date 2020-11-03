package com.tesda8.region8.util.enums;

public enum TTIType {
    RTC("RTC-Leyte"),
    CNVS("CNVS-Leyte"),
    PTC_LEYTE("PTC-Leyte"),
    CNSAT("CNSAT-Leyte"),
    PTC_BILIRAN("PTC-Biliran"),
    PTC_SOUTHERN_LEYTE("PTC-Southern Leyte"),
    PTC_SAMAR("PTC-Samar"),
    PTC_EASTERN_SAMAR("PTC-Eastern Samar"),
    SNSAT("SNSAT-Eastern Samar"),
    BNAS("BNAS-Eastern Samar"),
    ANAS("ANAS-Eastern Samar"),
    PTC_NORTHERN_SAMAR("PTC-Northern Samar"),
    BCAT("BCAT-Northern Samar"),
    LNAIS("LNAIS-Northern Samar"),

    TOTAL("Region 8");

    public final String label;

    private TTIType(String label) {
        this.label = label;
    }
}
