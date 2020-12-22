package com.tesda8.region8.reports.model.dto;

import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.util.enums.TTIType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TTIReportDto {

    private long id;
    private TTIType ttiType;
    private ReportSourceType reportSourceType;
    private EgacDataDto egacDataDto;
}
