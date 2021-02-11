package com.tesda8.region8.quality.service;

import com.tesda8.region8.quality.model.dto.CustomerFilter;
import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.dto.SummaryReportDto;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import com.tesda8.region8.quality.model.entities.Customer;
import com.tesda8.region8.quality.model.entities.Feedback;

import java.util.List;

public interface FeedbackService {

    void createFeedback(FeedbackDto feedbackDto);

    List<FeedbackDto> fetchAllCustomerFeedbacks(CustomerFilter customerFilter);

    SummaryReportDto fetchSummaryReport(SummaryReportFilter summaryReportFilter);

    List<FeedbackDto> fetchMonitoringReport(SummaryReportFilter summaryReportFilter);
}
