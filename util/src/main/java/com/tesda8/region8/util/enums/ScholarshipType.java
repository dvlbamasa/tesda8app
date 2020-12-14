package com.tesda8.region8.util.enums;

public enum ScholarshipType {

    TWSP("TWSP"),
    PESFA("PESFA"),
    STEP("STEP"),
    RESP("RESP"),
    UAQTEA_SB("UAQTEA SB"),
    UAQTEA_DIPLOMA("UAQTEA DIPLOMA");

    public final String label;

    private ScholarshipType(String label) {
        this.label = label;
    }
}
