package com.tesda8.region8.web.controller.quality;

import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.quality.model.dto.CustomerFilter;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import com.tesda8.region8.quality.service.ExcelParserService;
import com.tesda8.region8.quality.service.FeedbackService;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.web.controller.HeaderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Controller
public class QualityController extends HeaderController {

    private FeedbackService feedbackService;
    private ExcelParserService excelParserService;

    @Autowired
    public QualityController(RegisteredProgramStatusService registeredProgramStatusService,
                             ExpiredCertificateService expiredCertificateService,
                             FeedbackService feedbackService,
                             ExcelParserService excelParserService) {
        super(registeredProgramStatusService, expiredCertificateService);
        this.feedbackService = feedbackService;
        this.excelParserService = excelParserService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quality")
    public String quality(Model model) {
        model.addAttribute("feedbacks", feedbackService.fetchAllCustomerFeedbacks(new CustomerFilter()));
        model.addAttribute("customerFilter", new CustomerFilter());
        addStatusCounterToModel(model);
        return "quality/quality";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/quality/search")
    public String qualitySearch(@ModelAttribute CustomerFilter customerFilter, BindingResult bindingResult,
                                Model model) {

        model.addAttribute("feedbacks", feedbackService.fetchAllCustomerFeedbacks(customerFilter));
        model.addAttribute("customerFilter", customerFilter);
        addStatusCounterToModel(model);
        return "quality/quality";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quality/feedback-analysis")
    public String feedbackAnalysis(Model model) {
        SummaryReportFilter summaryReportFilter =
                new SummaryReportFilter(ApplicationUtil.convertToDateViaInstant(ApplicationUtil.getLocalDateTimeNow().truncatedTo(ChronoUnit.DAYS)),
                        ApplicationUtil.convertToDateViaInstant(ApplicationUtil.getLocalDateTimeNow().truncatedTo(ChronoUnit.DAYS)));
        model.addAttribute("summaryFilter", summaryReportFilter);
        model.addAttribute("summaryReport", feedbackService.fetchSummaryReport(summaryReportFilter));
        model.addAttribute("monitoringReport", feedbackService.fetchMonitoringReport(summaryReportFilter));
        addStatusCounterToModel(model);
        return "quality/feedback_analysis";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/quality/feedback-analysis/search")
    public String feedbackAnalysisSearch(@ModelAttribute SummaryReportFilter summaryReportFilter,
                                         BindingResult bindingResult,
                                         Model model) {
        model.addAttribute("summaryFilter", summaryReportFilter);
        model.addAttribute("summaryReport", feedbackService.fetchSummaryReport(summaryReportFilter));
        model.addAttribute("monitoringReport", feedbackService.fetchMonitoringReport(summaryReportFilter));
        addStatusCounterToModel(model);
        return "quality/feedback_analysis";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quality/monitoring-report/download")
    public void downloadMonitoringReport(@RequestParam("dateFrom") Date dateFrom,
                                         @RequestParam("dateTo") Date dateTo,
                                         HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateFromLabel = dateFormatter.format(dateFrom);
        String dateToLabel = dateFormatter.format(dateTo);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Monitoring-Report for " + dateFromLabel + " to " + dateToLabel + ".xlsx";
        response.setHeader(headerKey, headerValue);
        excelParserService.parseMonitoringReport(response,
                new SummaryReportFilter(ApplicationUtil.convertToDateViaInstant(ApplicationUtil.convertToLocalDateTimeViaInstant(dateFrom).truncatedTo(ChronoUnit.DAYS)),
                        ApplicationUtil.convertToDateViaInstant(ApplicationUtil.convertToLocalDateTimeViaInstant(dateTo).truncatedTo(ChronoUnit.DAYS))));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quality/summary-report/download")
    public void downloadSummaryReport(@RequestParam("dateFrom") Date dateFrom,
                                         @RequestParam("dateTo") Date dateTo,
                                         HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateFromLabel = dateFormatter.format(dateFrom);
        String dateToLabel = dateFormatter.format(dateTo);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Summary-Report for " + dateFromLabel + " to " + dateToLabel + ".xlsx";
        response.setHeader(headerKey, headerValue);
        excelParserService.parseSummaryReport(response,
                new SummaryReportFilter(ApplicationUtil.convertToDateViaInstant(ApplicationUtil.convertToLocalDateTimeViaInstant(dateFrom).truncatedTo(ChronoUnit.DAYS)),
                        ApplicationUtil.convertToDateViaInstant(ApplicationUtil.convertToLocalDateTimeViaInstant(dateTo).truncatedTo(ChronoUnit.DAYS))));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quality/customer-feedback/download")
    public void downloadCustomerFeedback(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Customer Feedback.xlsx";
        response.setHeader(headerKey, headerValue);
        excelParserService.parseCustomerFeedback(response, id);
    }
}
