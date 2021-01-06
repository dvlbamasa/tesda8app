package com.tesda8.region8.web.controller;

import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.reports.model.dto.MonthlyGraphFilter;
import com.tesda8.region8.reports.model.entities.DailyReportInfo;
import com.tesda8.region8.reports.service.DailyReportInfoService;
import com.tesda8.region8.util.service.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController extends DefaultController{

    private DailyReportInfoService dailyReportInfoService;

    @Autowired
    public HomeController(DailyReportInfoService dailyReportInfoService,
                          RegisteredProgramStatusService registeredProgramStatusService) {
        super(registeredProgramStatusService);
        this.dailyReportInfoService = dailyReportInfoService;
    }

    @GetMapping("/")
    public String home1(Model model) {
        addStatusCounterToModel(model);
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard/daily")
    public String daily(Model model) {
        addStatusCounterToModel(model);
        DailyReportInfo dailyReportInfo = dailyReportInfoService.getLatestDailyReportInfo();
        model.addAttribute("dailyReportInfo", dailyReportInfo.getUpdatedDate().plusHours(8));
        model.addAttribute("dateTimeNow", LocalDateTime.now().plusHours(8));
        return "dashboard/daily_reports";
    }

    @GetMapping("/dashboard/monthly")
    public String monthly(Model model) {
        addStatusCounterToModel(model);
        model.addAttribute("filter", new MonthlyGraphFilter(Math.toIntExact(ApplicationUtil.getCurrentYear())));
        return "dashboard/monthly_reports";
    }

    @GetMapping("/certification")
    public String certification(Model model) {
        addStatusCounterToModel(model);
        return "certification/certification";
    }

    @GetMapping("/payroll")
    public String payroll() {
        return "payroll/payroll";
    }

    @GetMapping("/supply")
    public String supply() {
        return "supply/supply";
    }

    @GetMapping("/login")
    public String login(Model model) {
        addStatusCounterToModel(model);
        return "login";
    }

    @GetMapping("/403")
    public String error403(Model model) {
        addStatusCounterToModel(model);
        return "error/403";
    }

    @GetMapping("/error")
    public String error500(Model model) {
        addStatusCounterToModel(model);
        return "error/500";
    }
}
