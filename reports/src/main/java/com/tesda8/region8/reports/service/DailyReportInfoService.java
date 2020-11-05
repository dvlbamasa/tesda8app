package com.tesda8.region8.reports.service;

import com.tesda8.region8.reports.model.entities.DailyReportInfo;

public interface DailyReportInfoService {

    DailyReportInfo getLatestDailyReportInfo();

    void saveDailyReportInfo(DailyReportInfo dailyReportInfo);
}
