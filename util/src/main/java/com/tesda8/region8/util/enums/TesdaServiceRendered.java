package com.tesda8.region8.util.enums;

public enum TesdaServiceRendered {

    COMPETENCY_ASSESSMENT("Competency Assessment", "Assessment and Certification"),
    CERTIFICATION("Certification (NC/COC/NTTC/TMC)", "Assessment and Certification"),
    ACCREDITATION("Accreditation (Application/Renewal)", "Assessment and Certification"),
    OTHERS_ASSESSMENT("Others", "Assessment and Certification"),

    APPLICATION_PROG_REG("Application", "Program Registration"),
    RE_REGISTRATION("Re-registration", "Program Registration"),
    OTHERS_PROG_REG("Others", "Program Registration"),

    REGULAR("Regular", "Training"),
    SCHOLARSHIP("Scholarship", "Training"),
    CAV_SO("CAV/SO", "Training"),
    OTHERS_TRAINING("Others", "Training"),

    OTHERS_EMPLOYMENT("Others", "Employment"),
    ADMIN("Admin", "Employment");

    public final String label;
    public final String serviceType;

    private TesdaServiceRendered(String label, String serviceType) {
        this.label = label;
        this.serviceType = serviceType;
    }
}
