package com.tesda8.region8.reports.service;

import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;

import java.util.List;

public interface GeneralReportService {

    List<GeneralReportDto> findAllGeneralReport();

    List<GeneralReportDto> findAllGeneralReportByEgacTypeAndDailyReportType(EgacType egacType,
                                                                            DailyReportType dailyReportType);

    List<GeneralReportDto> findAllGeneralReportByDailyReportType(DailyReportType dailyReportType);

    List<GeneralReportDto> findAllGeneralReportByDailyReportTypeAndReportSourceType(DailyReportType dailyReportType,
                                                                                    ReportSourceType reportSourceType);

    List<GeneralReportDto> findAllGeneralReportByDailyReportTypeAndReportSourceTypeAndEgacType(DailyReportType dailyReportType,
                                                                                               ReportSourceType reportSourceType,
                                                                                               EgacType egacType);

    List<GeneralReportDto> updateGeneralReports(List<GeneralReportDto> generalReports);

}
