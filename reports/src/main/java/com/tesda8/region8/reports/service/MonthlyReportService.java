package com.tesda8.region8.reports.service;

import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.reports.model.dto.MonthlyReportDto;
import com.tesda8.region8.util.enums.Month;

import java.util.List;

public interface MonthlyReportService {

    List<MonthlyReportDto> fetchMonthlyReport(int year);

    void updateMonthlyReport(List<GeneralReportDto> generalReports, Month month, int year);

    void deleteMonthlyReport(Month month, int year);
}
