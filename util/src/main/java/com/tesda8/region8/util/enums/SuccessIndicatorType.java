package com.tesda8.region8.util.enums;

public enum SuccessIndicatorType {
    RO_PO_TTI("RO/PO/TTI"),
    RO_PO("RO/PO"),
    TTI("TTI");

    public final String label;

    private SuccessIndicatorType(String label) {
        this.label = label;
    }
}
