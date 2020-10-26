package com.tesda8.region8.web.service;

import com.tesda8.region8.web.model.entities.DailyReportInfo;

public interface DailyReportInfoService {

    DailyReportInfo getLatestDailyReportInfo();

    void saveDailyReportInfo(DailyReportInfo dailyReportInfo);
}
