package com.tesda8.region8.web.model.dto.wrapper;

import com.tesda8.region8.web.model.dto.ROPerModeReportDto;
import lombok.Data;

import java.util.List;

@Data
public class ROPerModeReportWrapper {

    private List<ROPerModeReportDto> roPerModeGSReports;
    private List<ROPerModeReportDto> roPerModeT2Reports;
}
