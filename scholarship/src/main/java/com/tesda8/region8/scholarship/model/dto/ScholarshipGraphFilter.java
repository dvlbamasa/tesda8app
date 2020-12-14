package com.tesda8.region8.scholarship.model.dto;

import com.tesda8.region8.util.enums.ScholarshipType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScholarshipGraphFilter {
    private Long year;
    private ScholarshipType scholarshipType;

    public ScholarshipGraphFilter(Long year, ScholarshipType scholarshipType) {
        this.year = year;
        this.scholarshipType = scholarshipType;
    }
}
