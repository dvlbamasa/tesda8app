package com.tesda8.region8.util.enums;

public enum InstitutionType {

    PUBLIC("Public"),
    PRIVATE("Private"),
    ALL("All");

    public final String label;

    private InstitutionType(String label) {
        this.label = label;
    }
}
