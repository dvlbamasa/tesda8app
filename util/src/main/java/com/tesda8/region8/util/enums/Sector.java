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
    CONSTRUCTION_METALS("Construction and Metals and Engineering", "BUNDLED"),
    HEALTH_SOCIAL("Human Health / Health Care & Social, Community Development and Other Services", "BUNDLED"),
    FOODS_TOURISM_SOCIAL("Processed Food and Beverage, Tourism and Social, Community Development and Other Services", "BUNDLED"),
    HEALTH_AUTOMOTIVE("Human Health/Health Care and Automotive and Land Transportation", "BUNDLED"),
    FOODS_TOURISM("Processed Food and Beverages & Tourism", "BUNDLED"),
    AUTOMOTIVE_METALS("Automotive and Land Transportation & Metals and Engineering", "BUNDLED"),
    AUTOMOTIVE_TOURISM("Automotive and Land Transportation and Tourism", "BUNDLED"),
    GARMENTS_CONSTRUCTION("Garments and Construction", "BUNDLED"),
    ELECTRICAL_AUTOMOTIVE("Electrical & Electronics and   Automotive and Land Transport", "BUNDLED"),
    ELECTRICAL_METALS("Construction & Metals and Engineering", "BUNDLED"),
    GARMENTS_TOURISM("Garments and Tourism", "BUNDLED"),
    HEALTH_TOURISM("Human Health and Tourism", "BUNDLED"),
    AUTOMOTIVE_HVACR("Automotive and Land Transport and HVAC/R", "BUNDLED"),
    CONSTRUCTION_METALS_AUTOMOTIVE("Construction, Metals  and Engineering & Automotive & Land Transport", "BUNDLED"),
    AGRICULTURE_TOURISM_FOODS("Agriculture, Forestry and Fishery, Tourism & Processed Food and Beverage", "BUNDLED"),
    AGRICULTURE_FOODS("Agriculture, Forestry and Fishery  & Processed Food and Beverage", "BUNDLED"),
    SOCIAL_AUTOMOTIVE("Social, Community Development and Other Services and Automotive and Human Health", "BUNDLED"),
    CONSTRUCTION_ELECTRICAL("Construction and Electrical and Electronics ", "BUNDLED"),
    TOURISM_AUTOMOTIVE("Tourism & Automotive and Land Transport", "BUNDLED"),
    CONSTRUCTION_ICT("Construction and ICT", "BUNDLED"),
    SOCIAL_TOURISM("Social, Community Development and Other Services and Tourism", "BUNDLED"),
    AUTOMOTIVE_CONSTRUCTION("Construction & Automotive and Land Transport", "BUNDLED"),


    ALL("All Sectors", "TTI");



    public final String label;
    public final String sectorType;

    private Sector(String label, String sectorType) {
        this.label = label;
        this.sectorType = sectorType;
    }
}
