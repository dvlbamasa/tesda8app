package com.tesda8.region8.audit.model.enums;

public enum AuditAction {
    ALL("All", "ALL"),

    LOGIN("User Login", "LOGIN"),

    CREATE_OFFICIAL_ENTITY_AUDIT_EVENT("Create Official Entity", "PROGRAM_REGISTRATION"),
    UPDATE_OFFICIAL_ENTITY_AUDIT_EVENT("Update Official Entity", "PROGRAM_REGISTRATION"),

    CREATE_TRAINER_ENTITY_AUDIT_EVENT("Create Trainer Entity", "PROGRAM_REGISTRATION"),
    UPDATE_TRAINER_ENTITY_AUDIT_EVENT("Update Trainer Entity", "PROGRAM_REGISTRATION"),

    CREATE_STAFF_ENTITY_AUDIT_EVENT("Create Non Teaching Staff Entity", "PROGRAM_REGISTRATION"),
    UPDATE_STAFF_ENTITY_AUDIT_EVENT("Update Non Teaching Staff Entity", "PROGRAM_REGISTRATION"),

    CREATE_INSTITUTION_ENTITY_AUDIT_EVENT("Create Institution Entity", "PROGRAM_REGISTRATION"),
    UPDATE_INSTITUTION_ENTITY_AUDIT_EVENT("Update Institution Entity", "PROGRAM_REGISTRATION"),

    CREATE_REGISTERED_PROGRAM_ENTITY_AUDIT_EVENT("Create Registered Program Entity", "PROGRAM_REGISTRATION"),
    UPDATE_REGISTERED_PROGRAM_ENTITY_AUDIT_EVENT("Update Registered Program  Entity", "PROGRAM_REGISTRATION"),

    CREATE_SCHOLARSHIP_ENTITY_AUDIT_EVENT("Create Scholarship Accomplishment Entity", "SCHOLARSHIP"),
    UPDATE_SCHOLARSHIP_ENTITY_AUDIT_EVENT("Update Scholarship Accomplishment Entity", "SCHOLARSHIP"),

    CREATE_PAP_ENTITY_AUDIT_EVENT("Create P/A/P Entity", "PLANNING"),
    UPDATE_PAP_ENTITY_AUDIT_EVENT("Update P/A/P Entity", "PLANNING"),

    CREATE_SUCCESS_INDICATOR_ENTITY_AUDIT_EVENT("Create Success Indicator Entity", "PLANNING"),
    UPDATE_SUCCESS_INDICATOR_ENTITY_AUDIT_EVENT("Update Success Indicator Entity", "PLANNING"),

    CREATE_OPCR_ENTITY_AUDIT_EVENT("Create OPCR Entity", "PLANNING"),
    UPDATE_OPCR_ENTITY_AUDIT_EVENT("Update OPCR Entity", "PLANNING"),

    CREATE_CERTIFICATE_AUDIT_EVENT("Create Certificate Entity", "CERTIFICATION"),
    UPDATE_CERTIFICATE_AUDIT_EVENT("Update Certificate Entity", "CERTIFICATION"),

    UPDATE_REPORT_ENTITY_AUDIT_EVENT("Update Daily Accomplishment Report Entity", "REPORTS"),

    CREATE_MONTHLY_REPORT_ENTITY_AUDIT_EVENT("Create Monthly Accomplishment Report Entity", "REPORTS");

    public final String label;
    public final String auditType;

    private AuditAction(String label, String auditType) {
        this.label = label;
        this.auditType = auditType;
    }
}
