package com.tesda8.region8.util.enums;

public enum CourseStatus {

    WTR("WTR"),
    BUNDLED_PROGRAM("Bundled Program"),
    NTR("NTR"),
    WTR_COC("WTR (CoC)"),

    ALL("All Status");

    public final String label;

    private CourseStatus(String label) {
        this.label = label;
    }
}
