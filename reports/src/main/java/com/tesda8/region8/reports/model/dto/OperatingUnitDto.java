package com.tesda8.region8.reports.model.dto;

import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OperatingUnitDto {

    private long id;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private OperatingUnitType operatingUnitType;
    private List<MonthlyReportDto> monthlyReports;
}
