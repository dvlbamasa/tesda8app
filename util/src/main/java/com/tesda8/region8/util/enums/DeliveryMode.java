package com.tesda8.region8.util.enums;

public enum DeliveryMode {
    INSTITUTION("Institution-Based"),
    ENTERPRISE("Enterprise-Based"),
    COMMUNITY("Community-Based"),

    TOTAL("Total");

    public final String label;

    private DeliveryMode(String label) {
        this.label = label;
    }
}
