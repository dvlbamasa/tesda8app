package com.tesda8.region8.util.enums;

public enum MoaValidityType {
    OPEN("Open"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5");

    public final String label;

    private MoaValidityType(String label) {
        this.label = label;
    }
}
