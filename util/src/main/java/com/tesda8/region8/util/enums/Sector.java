package com.tesda8.region8.util.enums;

public enum Sector {
    AGRICULTURE("Agriculture, Forestry and Fishery", "TTI"),
    AUTOMOTIVE("Automotive and Land Transportation", "TTI"),
    CONSTRUCTION("Construction", "TTI"),
    ELECTRICAL("Electrical and Electronics", "TTI"),
    GARMENTS("Garments", "TTI"),
    HEALTH("Human Health and Health Care Services", "TTI"),
    HVACR("HVACR", "TTI"),
    ICT("ICT", "TTI"),
    METALS("Metals and Engineering", "TTI"),
    LSI("FL (LSI)", "TTI"),
    PROCESSED_FOODS("Processed foods and beverages", "TTI"),
    SOCIAL("Social Community Development and Other Services", "TTI"),
    TOURISM("Tourism", "TTI"),
    TVET("TVET", "TTI"),
    VISUAL_ARTS("Visual Arts", "NON-TTI"),
    MARITIME("Maritime", "NON-TTI"),
    CONSTRUCTION_METALS("Construction and Metals and Engineering", "NON-TTI"),

    ALL("ALL SECTORS", "TTI");



    public final String label;
    public final String sectorType;

    private Sector(String label, String sectorType) {
        this.label = label;
        this.sectorType = sectorType;
    }
}
