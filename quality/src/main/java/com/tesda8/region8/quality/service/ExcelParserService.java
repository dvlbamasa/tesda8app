package com.tesda8.region8.quality.service;

import com.tesda8.region8.quality.model.dto.SummaryReportFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelParserService {

    void parseMonitoringReport(HttpServletResponse response, SummaryReportFilter summaryReportFilter) throws IOException;

    void parseSummaryReport(HttpServletResponse response, SummaryReportFilter summaryReportFilter) throws IOException;

    void parseCustomerFeedback(HttpServletResponse response, Long id) throws IOException;

}
