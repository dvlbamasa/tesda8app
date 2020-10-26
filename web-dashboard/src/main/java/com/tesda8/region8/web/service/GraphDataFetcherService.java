package com.tesda8.region8.web.service;

import com.tesda8.region8.util.model.DataPoints;
import com.tesda8.region8.web.model.dto.graph.GraphDataList;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.DataPointType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;

import java.util.List;

public interface GraphDataFetcherService {

    List<DataPoints> fetchCertificationRateData(DataPointType dataPointType);

    List<DataPoints> fetchROPerModeReportsData(DataPointType dataPointType, EgacType egacType, ReportSourceType reportSourceType);

    List<DataPoints> fetchGeneralReportsData(DataPointType dataPointType, EgacType egacType, ReportSourceType reportSourceType,
                                             DailyReportType dailyReportType);

    List<DataPoints> fetchMonthlyReportsData(DataPointType dataPointType, OperatingUnitType operatingUnitType, EgacType egacType);


    GraphDataList fetchGeneralDataList(EgacType egacType, ReportSourceType reportSourceType,
                                       DailyReportType dailyReportType);

    GraphDataList fetchCertificationRateDataList();

    GraphDataList fetchROPerModeReportsDataList(EgacType egacType, ReportSourceType reportSourceType);

    GraphDataList fetchMonthlyReportDataList(EgacType egacType, OperatingUnitType operatingUnitType);
}
