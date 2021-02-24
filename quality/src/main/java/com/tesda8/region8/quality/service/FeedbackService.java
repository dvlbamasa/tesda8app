package com.tesda8.region8.quality.service;

import com.tesda8.region8.quality.model.dto.CustomerFilter;
import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.dto.SummaryReportDto;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackService {

    String generateControlNumber(LocalDateTime localDateTime);

    void createFeedback(FeedbackDto feedbackDto);

    Page<FeedbackDto> fetchAllCustomerFeedbacks(int pageNumber, int pageSize, CustomerFilter customerFilter);

    FeedbackDto getFeedback(Long id);

    SummaryReportDto fetchSummaryReport(SummaryReportFilter summaryReportFilter);

    List<FeedbackDto> fetchMonitoringReport(SummaryReportFilter summaryReportFilter);
}
