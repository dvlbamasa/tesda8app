package com.tesda8.region8.util.enums;

public enum EducationalAttainment {

    HIGHSCHOOL("High School Graduate"),
    COLLEGE_UNDERGRAD("College Undergraduate"),
    COLLEGE_GRAD("College Graduate/Bachelor's Degree"),
    COLLEGE_POSTGRAD("College Post Graduate/Master's Degree"),
    VOCATIONAL("Vocational/TVET");

    public final String label;

    private EducationalAttainment(String label) {
        this.label = label;
    }

}
