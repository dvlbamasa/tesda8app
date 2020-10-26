package com.tesda8.region8.util.enums;

public enum DataPointType {
    TARGET("Target"),
    OUTPUT("Output"),
    ASSESSED("Assessed"),
    CERTIFIED("Certified"),
    RATE("Rate");

    public final String label;

    private DataPointType(String label) {
        this.label = label;
    }
}
