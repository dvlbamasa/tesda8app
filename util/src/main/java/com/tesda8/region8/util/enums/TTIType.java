package com.tesda8.region8.util.enums;

public enum TTIType {
    RTC("RTC-Leyte"),
    CNVS("CNVS-Leyte"),
    PTC_LEYTE("PTC-Leyte"),
    CNSAT("CNSAT-Leyte"),
    PTC_BILIRAN("PTC-Biliran"),
    PTC_SOUTHERN_LEYTE("PTC-S.Leyte"),
    PTC_SAMAR("PTC-Samar"),
    PTC_EASTERN_SAMAR("PTC-E.Samar"),
    SNSAT("SNSAT-E.Samar"),
    BNAS("BNAS-E.Samar"),
    ANAS("ANAS-E.Samar"),
    PTC_NORTHERN_SAMAR("PTC-N.Samar"),
    BCAT("BCAT-N.Samar"),
    LNAIS("LNAIS-N.Samar"),

    TOTAL("Region 8");

    public final String label;

    private TTIType(String label) {
        this.label = label;
    }
}
