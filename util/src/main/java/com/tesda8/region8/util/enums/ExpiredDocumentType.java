package com.tesda8.region8.util.enums;

public enum ExpiredDocumentType {
    ALL("All"),
    BUILDING_OWNERSHIP("Expired Building Ownership"),
    FIRE_SAFETY("Expired Fire Safety Certificate"),
    MOA_VALIDITY("Expired MOA Validity");

    public final String label;

    private ExpiredDocumentType(String label) {
        this.label = label;
    }
}
