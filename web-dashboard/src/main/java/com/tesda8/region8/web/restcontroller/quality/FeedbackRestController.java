package com.tesda8.region8.web.restcontroller.quality;

import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.dto.SummaryReportDto;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import com.tesda8.region8.quality.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quality")
public class FeedbackRestController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackRestController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/summary-report")
    public SummaryReportDto getSummaryReportDto(@RequestBody SummaryReportFilter summaryReportFilter) {
        return feedbackService.fetchSummaryReport(summaryReportFilter);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/monitoring-report")
    public List<FeedbackDto> getMonitoringReportDto(@RequestBody SummaryReportFilter summaryReportFilter) {
        return feedbackService.fetchMonitoringReport(summaryReportFilter);
    }
}
