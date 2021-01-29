package com.tesda8.region8.util.enums;

public enum CertificateType {
    NC("National Certificate"),
    TM("Trainers Methodology"),
    NTTC("National TVET Trainer Certificate");

    public final String label;

    private CertificateType(String label) {
        this.label = label;
    }
}
