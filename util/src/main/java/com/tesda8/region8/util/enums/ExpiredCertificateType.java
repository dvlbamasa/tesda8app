package com.tesda8.region8.util.enums;

public enum ExpiredCertificateType {
    ALL("All"),
    NC("Expired NC"),
    TMC("Expired TMC"),
    NTTC("Expired NTTC");

    public final String label;

    private ExpiredCertificateType (String label) {
        this.label = label;
    }
}
