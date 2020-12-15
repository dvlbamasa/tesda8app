package com.tesda8.region8.reports.model.wrapper;

import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class GeneralReportDataRowWrapper {

    private OperatingUnitType operatingUnitType;
    private GeneralReportDto enrolled;
    private GeneralReportDto graduates;
    private GeneralReportDto assessed;
    private GeneralReportDto certified;
}
