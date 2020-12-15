package com.tesda8.region8.reports.model.wrapper;

import com.tesda8.region8.reports.model.dto.TTIReportDto;
import com.tesda8.region8.util.enums.TTIType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TTIReportDataRowWrapper {

    private TTIType ttiType;
    private TTIReportDto enrolled;
    private TTIReportDto graduates;
    private TTIReportDto assessed;
    private TTIReportDto certified;
}
