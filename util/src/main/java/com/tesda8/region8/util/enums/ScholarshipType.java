package com.tesda8.region8.util.enums;

public enum ScholarshipType {

    TWSP("TWSP"),
    PESFA("PESFA"),
    STEP("STEP"),
    UAQTEA_SB("UAQTEA_SB"),
    UAQTEA_DIPLOMA("UAQTEA_DIPLOMA");

    public final String label;

    private ScholarshipType(String label) {
        this.label = label;
    }
}
