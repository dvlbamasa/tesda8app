package com.tesda8.region8.reports.model.dto;

import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CertificationRateReportDto {

    private long id;
    private OperatingUnitType operatingUnitType;
    private long assessed;
    private long certified;
    private double rate;
}
