package com.tesda8.region8.util.enums;

public enum Module {
    LOGIN("LOGIN", "Login"),
    REPORTS("REPORTS", "Reports/Dashboard"),
    PLANNING("PLANNING", "Planning"),
    PROGRAM_REGISTRATION("PROGRAM_REGISTRATION", "Program Registration"),
    SCHOLARSHIP("SCHOLARSHIP", "Scholarship"),
    CERTIFICATION("CERTIFICATION", "Certification");

    public final String type;
    public final String label;

    private Module(String type, String label) {
        this.type = type;
        this.label = label;
    }
}
