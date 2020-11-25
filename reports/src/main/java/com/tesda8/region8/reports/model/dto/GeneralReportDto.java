package com.tesda8.region8.reports.model.dto;

import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class GeneralReportDto {

    private long id;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private OperatingUnitType operatingUnitType;
    private ReportSourceType reportSourceType;
    private EgacDataDto egacDataDto;
    private DailyReportType dailyReportType;
}
