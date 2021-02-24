package com.tesda8.region8.web.controller.quality;

import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.quality.model.dto.CustomerFilter;
import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import com.tesda8.region8.quality.service.ExcelParserService;
import com.tesda8.region8.quality.service.FeedbackService;
import com.tesda8.region8.util.enums.Sex;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.web.controller.HeaderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Controller
public class QualityController extends HeaderController {

    private FeedbackService feedbackService;
    private ExcelParserService excelParserService;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");


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
        Page<FeedbackDto> feedbackDtoPage = feedbackService.fetchAllCustomerFeedbacks(ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), new CustomerFilter());
        model.addAttribute("feedbacks", feedbackDtoPage.getContent());
        model.addAttribute("customerFilter", new CustomerFilter());
        model.addAttribute("totalPages", feedbackDtoPage.getTotalPages());
        model.addAttribute("totalElements", feedbackDtoPage.getTotalElements());
        model.addAttribute("currentPage", ApplicationUtil.getDefaultPageNumber());
        addStatusCounterToModel(model);
        return "quality/quality";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/quality/search")
    public String qualitySearch(@ModelAttribute CustomerFilter customerFilter, BindingResult bindingResult,
                                Model model) {
        Page<FeedbackDto> feedbackDtoPage = feedbackService.fetchAllCustomerFeedbacks(ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), customerFilter);
        model.addAttribute("feedbacks", feedbackDtoPage.getContent());
        model.addAttribute("totalPages", feedbackDtoPage.getTotalPages());
        model.addAttribute("totalElements", feedbackDtoPage.getTotalElements());
        model.addAttribute("currentPage", ApplicationUtil.getDefaultPageNumber());
        model.addAttribute("customerFilter", customerFilter);
        addStatusCounterToModel(model);
        return "quality/quality";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quality/pagination")
    public String qualityPagination(@RequestParam("pageNumber") int pageNumber,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "gender", required = false) Sex gender,
                                    @RequestParam(value = "address", required = false) String address,
                                    @RequestParam(value = "contactNumber", required = false) String contactNumber,
                                    @RequestParam(value = "emailAddress", required = false) String emailAddress,
                                    @RequestParam(value = "auditDateFrom", required = false)
                                                String auditDateFrom,
                                    @RequestParam(value = "auditDateTo", required = false)
                                                String auditDateTo,
                                    Model model) throws ParseException {
        Date from = auditDateFrom.equals("") ? null : SIMPLE_DATE_FORMAT.parse(auditDateFrom);
        Date to = auditDateTo.equals("") ? null : SIMPLE_DATE_FORMAT.parse(auditDateTo);
        CustomerFilter customerFilter = new CustomerFilter(name, gender, address, contactNumber, emailAddress, from, to);
        Page<FeedbackDto> feedbackDtoPage = feedbackService.fetchAllCustomerFeedbacks(pageNumber, ApplicationUtil.getDefaultPageSize(), customerFilter);
        model.addAttribute("feedbacks", feedbackDtoPage.getContent());
        model.addAttribute("totalPages", feedbackDtoPage.getTotalPages());
        model.addAttribute("totalElements", feedbackDtoPage.getTotalElements());
        model.addAttribute("currentPage", pageNumber);
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
