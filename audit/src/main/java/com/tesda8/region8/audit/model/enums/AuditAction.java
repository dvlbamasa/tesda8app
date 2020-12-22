package com.tesda8.region8.audit.model.enums;

public enum AuditAction {
    CREATE_OFFICIAL_ENTITY_AUDIT_EVENT("Create Official Entity"),
    UPDATE_OFFICIAL_ENTITY_AUDIT_EVENT("Update Official Entity"),

    CREATE_TRAINER_ENTITY_AUDIT_EVENT("Create Trainer Entity"),
    UPDATE_TRAINER_ENTITY_AUDIT_EVENT("Update Trainer Entity"),

    CREATE_STAFF_ENTITY_AUDIT_EVENT("Create Non Teaching Staff Entity"),
    UPDATE_STAFF_ENTITY_AUDIT_EVENT("Update Non Teaching Staff Entity"),

    CREATE_INSTITUTION_ENTITY_AUDIT_EVENT("Create Institution Entity"),
    UPDATE_INSTITUTION_ENTITY_AUDIT_EVENT("Update Institution Entity"),

    CREATE_REGISTERED_PROGRAM_ENTITY_AUDIT_EVENT("Create Registered Program Entity"),
    UPDATE_REGISTERED_PROGRAM_ENTITY_AUDIT_EVENT("Update Registered Program  Entity"),

    CREATE_SCHOLARSHIP_ENTITY_AUDIT_EVENT("Create Scholarship Accomplishment Entity"),
    UPDATE_SCHOLARSHIP_ENTITY_AUDIT_EVENT("Update Scholarship Accomplishment Entity"),

    CREATE_PAP_ENTITY_AUDIT_EVENT("Create P/A/P Entity"),
    UPDATE_PAP_ENTITY_AUDIT_EVENT("Update P/A/P Entity"),

    CREATE_SUCCESS_INDICATOR_ENTITY_AUDIT_EVENT("Create Success Indicator Entity"),
    UPDATE_SUCCESS_INDICATOR_ENTITY_AUDIT_EVENT("Update Success Indicator Entity"),

    CREATE_OPCR_ENTITY_AUDIT_EVENT("Create OPCR Entity"),
    UPDATE_OPCR_ENTITY_AUDIT_EVENT("Update OPCR Entity"),

    UPDATE_REPORT_ENTITY_AUDIT_EVENT("Update Daily Accomplishment Report Entity"),

    CREATE_MONTHLY_REPORT_ENTITY_AUDIT_EVENT("Create Monthly Accomplishment Report Entity");

    public final String label;

    private AuditAction(String label) {
        this.label = label;
    }
}
