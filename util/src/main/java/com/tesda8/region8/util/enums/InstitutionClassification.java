package com.tesda8.region8.util.enums;

public enum  InstitutionClassification {

    FARM_SCHOOL("Farm School"),
    HEI("HEI"),
    LGU("LGU Run"),
    NGO("NGO/Foundation"),
    SUC("SUC"),
    TESDA("TESDA Technology Institution"),
    TVI("TVI"),
    TVI_NGO("TVI-NGO/Foundation"),

    ALL("All Classification");

    public final String label;

    private InstitutionClassification(String label) {
        this.label = label;
    }
}
