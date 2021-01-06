package com.tesda8.region8.web.controller.reports;

import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.reports.model.dto.MonthlyGraphFilter;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.web.controller.DefaultController;
import com.tesda8.region8.web.model.dto.wrapper.GeneralReportsDtoWrapper;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.reports.service.GeneralReportService;
import com.tesda8.region8.reports.service.MonthlyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class MonthlyReportController extends DefaultController {

    private MonthlyReportService monthlyReportService;
    private GeneralReportService generalReportService;

    @Autowired
    public MonthlyReportController(MonthlyReportService monthlyReportService,
                                   GeneralReportService generalReportService,
                                   RegisteredProgramStatusService registeredProgramStatusService) {
        super(registeredProgramStatusService);
        this.monthlyReportService = monthlyReportService;
        this.generalReportService = generalReportService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/monthlyReports")
    public String showMonthlyReports(Model model) {
        GeneralReportsDtoWrapper generalReportsDtoWrapper = new GeneralReportsDtoWrapper();
        generalReportsDtoWrapper.setPoReports(generalReportService.
                findAllGeneralReportByDailyReportType(DailyReportType.PO_REPORT));

        model.addAttribute("reports", generalReportsDtoWrapper);
        addStatusCounterToModel(model);
        return "daily_reports/update_monthly_reports";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/monthlyReports/update")
    public String updateMonthlyReports(@ModelAttribute GeneralReportsDtoWrapper generalReportsDtoWrapper,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        monthlyReportService.updateMonthlyReport(generalReportsDtoWrapper.getPoReports(), generalReportsDtoWrapper.getMonth(), generalReportsDtoWrapper.getYear());
        model.addAttribute("filter", new MonthlyGraphFilter(Math.toIntExact(ApplicationUtil.getCurrentYear())));
        return "redirect:/dashboard/monthly";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/monthlyReports/graph")
    public String filterGraph(@RequestParam("year") int year, Model model) {
        model.addAttribute("filter", new MonthlyGraphFilter(year));
        addStatusCounterToModel(model);
        return "dashboard/monthly_reports";
    }

}
