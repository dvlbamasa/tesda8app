package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class GeneralReport extends Auditable<String> {

    @Embedded
    private EgacData egacData;

    @Column(name = "OPERATING_UNIT")
    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    @Enumerated(EnumType.STRING)
    private ReportSourceType reportSourceType;

    @Enumerated(EnumType.STRING)
    private DailyReportType dailyReportType;
}
