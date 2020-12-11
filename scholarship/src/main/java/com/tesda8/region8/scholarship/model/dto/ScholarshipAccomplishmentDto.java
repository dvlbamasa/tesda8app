package com.tesda8.region8.scholarship.model.dto;

import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ScholarshipType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ScholarshipAccomplishmentDto {
    private Long id;
    private OperatingUnitType operatingUnitType;
    private ScholarshipType scholarshipType;
    private Month month;
    private Long year;
    private QualificationMapDto qualificationMapDto;
    private FinancialAccomplishmentDto financialAccomplishmentDto;
    private PhysicalAccomplishmentDto physicalAccomplishmentDto;

    public ScholarshipAccomplishmentDto(OperatingUnitType operatingUnitType, ScholarshipType scholarshipType,
                                        Month month, Long year) {
        this.operatingUnitType = operatingUnitType;
        this.scholarshipType = scholarshipType;
        this.month = month;
        this.year = year;
        this.qualificationMapDto = new QualificationMapDto(BigDecimal.ZERO, 0L);
        this.financialAccomplishmentDto = new FinancialAccomplishmentDto(new RoFinancialAccomplishmentDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO), new PoFinancialAccomplishmentDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
        this.physicalAccomplishmentDto = new PhysicalAccomplishmentDto(0L, 0.0, 0L, 0.0, 0L, 0.0, 0L, 0.0, 0L);
    }
}
