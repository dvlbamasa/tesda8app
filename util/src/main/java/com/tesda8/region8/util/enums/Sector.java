package com.tesda8.region8.util.enums;

public enum Sector {
    AGRICULTURE("Agriculture, Forestry and Fishery"),
    AUTOMOTIVE("Automotive and Land Transportation"),
    CONSTRUCTION("Construction"),
    ELECTRICAL("Electrical and Electronics"),
    GARMENTS("Garments"),
    HEALTH("Human Health and Health Care Services"),
    HVACR("HVACR"),
    ICT("ICT"),
    METALS("Metals and Engineering"),
    LSI("FL (LSI)"),
    PROCESSED_FOODS("Processed foods and beverages"),
    SOCIAL("Social Community Development and Other Services"),
    TOURISM("Tourism"),
    TVET("TVET");

    public final String label;

    private Sector(String label) {
        this.label = label;
    }
}
