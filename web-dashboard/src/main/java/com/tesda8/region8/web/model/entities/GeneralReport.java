package com.tesda8.region8.web.model.entities;

import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "GENERAL_REPORT")
public class GeneralReport extends GeneralData {

    @Embedded
    private EgacData egacData;

    @Column(name = "OPERATING_UNIT")
    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    @Column(name = "REPORT_SOURCE_TYPE")
    @Enumerated(EnumType.STRING)
    private ReportSourceType reportSourceType;

    @Column(name = "DAILY_REPORT_TYPE")
    @Enumerated(EnumType.STRING)
    private DailyReportType dailyReportType;
}
