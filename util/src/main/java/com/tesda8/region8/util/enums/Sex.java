package com.tesda8.region8.util.enums;

public enum Sex {
    ALL("All"),
    MALE("Male"),
    FEMALE("Female");

    public final String label;

    private Sex(String label) {
        this.label = label;
    }
}
