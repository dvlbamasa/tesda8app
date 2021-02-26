package com.tesda8.region8.util.enums;

public enum OperatingUnitPOType {

    LEYTE_PO("Leyte PO", "HIGHLIGHTED", "PO"),
    BILIRAN_PO("Biliran PO", "NON-HIGHLIGHTED", "PO"),
    SOUTHERN_LEYTE_PO("Southern Leyte PO", "HIGHLIGHTED", "PO"),
    SAMAR_PO("Samar PO", "NON-HIGHLIGHTED", "PO"),
    EASTERN_SAMAR_PO("Eastern Samar PO", "HIGHLIGHTED", "PO"),
    NORTHERN_SAMAR_PO("Northern Samar PO", "NON-HIGHLIGHTED", "PO"),
    TESDA_RO("TESDA RO", "HIGHLIGHTED", "PO"),

    RTC_TTI("RTC", "HIGHLIGHTED", "TTI"),
    CNVS_TTI("CNVS", "NON-HIGHLIGHTED", "TTI"),
    PTC_LEYTE_TTI("PTC Leyte", "HIGHLIGHTED", "TTI"),
    CNSAT_TTI("CNSAT", "NON-HIGHLIGHTED", "TTI"),
    PTC_BILIRAN_TTI("PTC Biliran", "HIGHLIGHTED", "TTI"),
    PTC_SO_LEYTE_TTI("PTC Southern Leyte", "NON-HIGHLIGHTED", "TTI"),
    PTC_SAMAR_TTI("PTC Samar", "HIGHLIGHTED", "TTI"),
    PTC_E_SAMAR_TTI("PTC Eastern Samar", "NON-HIGHLIGHTED", "TTI"),
    SNSAT_TTI("SNSAT", "HIGHLIGHTED", "TTI"),
    BNAS_TTI("BNAS", "NON-HIGHLIGHTED", "TTI"),
    ANAS_TTI("ANAS", "HIGHLIGHTED", "TTI"),
    PTC_N_SAMAR_TTI("PTC Northern Samar", "NON-HIGHLIGHTED", "TTI"),
    BCAT_TTI("BCAT", "HIGHLIGHTED", "TTI"),
    LNAIS_TTI("LNAIS", "NON-HIGHLIGHTED", "TTI"),

    TOTAL("Region 8", "NON-HIGHLIGHTED", "PO");

    public final String label;
    public final String tableType;
    public final String successIndicatorType;


    private OperatingUnitPOType(String label, String tableType, String successIndicatorType) {
        this.label = label;
        this.tableType = tableType;
        this.successIndicatorType = successIndicatorType;
    }
}
