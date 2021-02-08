package com.tesda8.region8.util.enums;

public enum Sex {
    ALL("All", "Lahat"),
    MALE("Male", "Lalaki"),
    FEMALE("Female", "Babae");

    public final String label;
    public final String label2;

    private Sex(String label, String label2) {
        this.label = label;
        this.label2 = label2;
    }
}
