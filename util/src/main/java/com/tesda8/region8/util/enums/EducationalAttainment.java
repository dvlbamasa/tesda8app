package com.tesda8.region8.util.enums;

public enum EducationalAttainment {
    ALL("All"),
    HIGHSCHOOL_UNDERGRAD("High School Undergraduate"),
    HIGHSCHOOL_GRAD("High School Graduate"),
    COLLEGE_UNDERGRAD("College Undergraduate"),
    COLLEGE_GRAD("College Graduate/Bachelor's Degree"),
    COLLEGE_POSTGRAD("College Post Graduate"),
    MASTERS("Master's Degree"),
    DOCTORATE("Doctorate/Doctoral Degree"),
    VOCATIONAL("Vocational/TVET"),

    NONE("Not Available");

    public final String label;

    private EducationalAttainment(String label) {
        this.label = label;
    }

}
