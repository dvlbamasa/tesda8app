package com.tesda8.region8.scholarship.model.dto;

import com.tesda8.region8.util.enums.Month;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScholarshipFilter {
    private Long year;
    private Month month;

    public ScholarshipFilter(Long year, Month month) {
        this.year = year;
        this.month = month;
    }
}
