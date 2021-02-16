package com.tesda8.region8.web.restcontroller.quality;

import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.dto.SummaryReportDto;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import com.tesda8.region8.quality.service.ExcelParserService;
import com.tesda8.region8.quality.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/quality")
public class FeedbackRestController {

    private FeedbackService feedbackService;
    private ExcelParserService excelParserService;

    @Autowired
    public FeedbackRestController(FeedbackService feedbackService, ExcelParserService excelParserService) {
        this.feedbackService = feedbackService;
        this.excelParserService = excelParserService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/summary-report")
    public SummaryReportDto getSummaryReportDto(@RequestBody SummaryReportFilter summaryReportFilter) throws IOException {
        return feedbackService.fetchSummaryReport(summaryReportFilter);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/monitoring-report")
    public List<FeedbackDto> getMonitoringReportDto(@RequestBody SummaryReportFilter summaryReportFilter) throws IOException {
        return feedbackService.fetchMonitoringReport(summaryReportFilter);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/feedback/{id}/fetch")
    public FeedbackDto getFeedback(@PathVariable("id") Long id) throws IOException {
        return feedbackService.getFeedback(id);
    }
}
