package com.tesda8.region8.reports.model.dto;

import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;

import java.util.Date;

@Data
public class CertificationRateReportDto {

    private long id;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private OperatingUnitType operatingUnitType;
    private long assessed;
    private long certified;
    private double rate;
}
