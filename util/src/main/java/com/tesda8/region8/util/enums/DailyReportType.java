package com.tesda8.region8.util.enums;

public enum DailyReportType {

    PO_REPORT("PO Report"),
    INSTITUTION_BASED_REPORT("Institution Based"),
    ENTERPRISE_BASED_REPORT("Enterprise Based"),
    COMMUNITY_BASED_REPORT("Community Based");

    public final String label;

    private DailyReportType(String label) {
        this.label = label;
    }
}
