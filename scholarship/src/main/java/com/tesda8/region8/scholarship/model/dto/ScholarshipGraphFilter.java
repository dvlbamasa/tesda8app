package com.tesda8.region8.scholarship.model.dto;

import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.ScholarshipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipGraphFilter {
    private Long year;
    private Month month;
    private ScholarshipType scholarshipType;

    public ScholarshipGraphFilter(Long year, ScholarshipType scholarshipType) {
        this.year = year;
        this.scholarshipType = scholarshipType;
    }
}
