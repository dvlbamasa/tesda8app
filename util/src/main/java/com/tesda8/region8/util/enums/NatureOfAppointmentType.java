package com.tesda8.region8.util.enums;

public enum NatureOfAppointmentType {

    REGULAR("Regular"),
    CONTRACTUAL("Contractual");

    public final String label;

    private NatureOfAppointmentType(String label) {
        this.label = label;
    }
}
