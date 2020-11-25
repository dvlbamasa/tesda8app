package com.tesda8.region8.scholarship.model.dto;

import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ScholarshipType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScholarshipAccomplishmentDto {

    private OperatingUnitType operatingUnitType;
    private ScholarshipType scholarshipType;
    private Month month;
    private Long year;
    private QualificationMapDto qualificationMapDto;
    private FinancialAccomplishmentDto financialAccomplishmentDto;
    private PhysicalAccomplishmentDto physicalAccomplishmentDto;
}
