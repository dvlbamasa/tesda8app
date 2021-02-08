package com.tesda8.region8.util.enums;

public enum ActionTaken {
    ASSESSORS_ACCREDITATION("Assessors Accreditation Certification released"),
    CERTIFICATE_RELEASED("Certificate (National Certificate/Certificate of Competency/Certificate Of Training/TM Certificate/NTTC) issued/released"),
    CERTIFICATE_AUTHENTICATED("Certificate/document authenticated"),
    INFORMATION_PROVIDED("Information Provided"),
    LETTER_RECEIVED("Letter/Communication/Correspondence/Application Documents received"),
    OTHERS("Others"),
    REFERRED_TO_FOCAL("Referred to focal person's concerned"),
    RELEASED_CAV("Released CAV");

    public final String label;

    private ActionTaken(String label) {
        this.label = label;
    }

}
