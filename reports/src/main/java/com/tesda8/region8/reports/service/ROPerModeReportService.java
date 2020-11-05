package com.tesda8.region8.reports.service;

import com.tesda8.region8.reports.model.dto.ROPerModeReportDto;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;

import java.util.List;

public interface ROPerModeReportService {

    List<ROPerModeReportDto> findAllROPerModeReports();

    List<ROPerModeReportDto> findAllROPerModeReportsByEgacTypeAndReportSourceType(EgacType egacType,
                                                                                  ReportSourceType reportSourceType);

    List<ROPerModeReportDto> findAllROPerModeReportsByReportSourceType(ReportSourceType reportSourceType);

    List<ROPerModeReportDto> updateROPerModeReports(List<ROPerModeReportDto> roPerModeReportDtos);
}
