package com.tesda8.region8.web.model.dto;

import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;
import lombok.Data;

import java.util.Date;

@Data
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
