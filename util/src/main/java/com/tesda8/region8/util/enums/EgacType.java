package com.tesda8.region8.util.enums;

public enum EgacType {
    ENROLLED("Enrolled"),
    GRADUATED("Graduated"),
    ASSESSED("Assessed"),
    CERTIFIED("Certified");
    
    public final String label;
    
    private EgacType(String label) {
        this.label = label;
    }
}
