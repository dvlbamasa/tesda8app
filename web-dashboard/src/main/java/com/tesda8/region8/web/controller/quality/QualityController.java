package com.tesda8.region8.web.controller.quality;

import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.quality.model.dto.CustomerFilter;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import com.tesda8.region8.quality.service.FeedbackService;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.web.controller.DefaultController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class QualityController extends DefaultController {

    private FeedbackService feedbackService;

    @Autowired
    public QualityController(RegisteredProgramStatusService registeredProgramStatusService,
                             FeedbackService feedbackService) {
        super(registeredProgramStatusService);
        this.feedbackService = feedbackService;
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
                new SummaryReportFilter(ApplicationUtil.convertToDateViaInstant(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)),
                        ApplicationUtil.convertToDateViaInstant(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)));
        model.addAttribute("summaryFilter", summaryReportFilter);
        model.addAttribute("summaryReport", feedbackService.fetchSummaryReport(summaryReportFilter));
        model.addAttribute("monitoringReport", feedbackService.fetchMonitoringReport(summaryReportFilter));
        addStatusCounterToModel(model);
        return "quality/summary_report";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/quality/feedback-analysis/search")
    public String feedbackAnalysisSearch(@ModelAttribute SummaryReportFilter summaryReportFilter,
                                         BindingResult bindingResult,
                                         Model model) {
        model.addAttribute("summaryFilter", summaryReportFilter);
        model.addAttribute("summaryReport", feedbackService.fetchSummaryReport(summaryReportFilter));
        model.addAttribute("monitoringReport", feedbackService.fetchMonitoringReport(summaryReportFilter));
        addStatusCounterToModel(model);
        return "quality/summary_report";
    }
}
