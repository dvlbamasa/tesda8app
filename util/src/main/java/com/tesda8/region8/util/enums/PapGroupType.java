package com.tesda8.region8.util.enums;

public enum PapGroupType {
    TESDPP("Technical Education and Skills Development Policy Program"),
    TESDRP("Technical Education and Skills Development Regulatory Program"),
    TESDP("Technical Education and Skills Development Programs"),
    STO("Support to Operations"),
    GASS("General Administrative Support Services");

    public final String label;

    private PapGroupType(String label) {
        this.label = label;
    }
}
