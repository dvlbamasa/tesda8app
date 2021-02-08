package com.tesda8.region8.util.enums;

public enum TesdaOffice {
    ORD("Office of the Regional Director (001)"),
    ROD("Regional Operations Division (001)"),
    FASD("Finance Administrative Services Division (001)"),
    BILIRAN_PO("Biliran Provincial Office (002)"),
    EASTERN_SAMAR_PO("Eastern Samar Provincial Office (003)"),
    LEYTE_PO("Leyte Provincial Office (004)"),
    NORTHERN_SAMAR_PO("Northern Samar Provincial Office (005)"),
    SAMAR_PO("Samar Provincial Office (006)"),
    SOUTHERN_LEYTE_PO("Southern Leyte Provincial Office (007)"),
    RTC("Regional Training Center (008)");

    public final String label;

    private TesdaOffice(String label) {
        this.label = label;
    }
}
