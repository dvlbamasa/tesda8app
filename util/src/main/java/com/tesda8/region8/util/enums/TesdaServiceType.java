package com.tesda8.region8.util.enums;

public enum TesdaServiceType {
    ASSESSMENT("Assessment and Certification"),
    PROGRAM_REGISTRATION("Program Registration"),
    TRAINING("Training"),
    EMPLOYMENT("Employment");

    public final String type;

    private TesdaServiceType(String type) {
        this.type = type;
    }
}
