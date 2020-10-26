package com.tesda8.region8.web.service;

import com.tesda8.region8.web.model.dto.CertificationRateReportDto;
import com.tesda8.region8.web.model.dto.GeneralReportDto;
import com.tesda8.region8.web.model.dto.ROPerModeReportDto;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;

import java.util.List;

public interface TableDataFetcherService {

    List<GeneralReportDto> fetchGeneralReportTableData(DailyReportType dailyReportType, ReportSourceType reportSourceType, EgacType egacType);

    List<CertificationRateReportDto> fetchCertificationRateTableData();

    List<ROPerModeReportDto> fetchROPerModeTableData(ReportSourceType reportSourceType, EgacType egacType);
}
