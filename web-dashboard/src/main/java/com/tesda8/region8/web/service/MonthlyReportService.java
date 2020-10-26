package com.tesda8.region8.web.service;

import com.tesda8.region8.web.model.dto.GeneralReportDto;
import com.tesda8.region8.util.enums.Month;

import java.util.List;

public interface MonthlyReportService {

    void updateMonthlyReport(List<GeneralReportDto> generalReports, Month month, int year);

    void deleteMonthlyReport(Month month, int year);
}
