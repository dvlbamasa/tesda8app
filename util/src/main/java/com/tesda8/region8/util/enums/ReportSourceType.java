package com.tesda8.region8.util.enums;

public enum ReportSourceType {
    T2MIS("T2MIS"),
    GS("Google Sheet");

    public final String label;

    private ReportSourceType(String label) {
        this.label = label;
    }
}
