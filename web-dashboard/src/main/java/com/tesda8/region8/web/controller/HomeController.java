package com.tesda8.region8.web.controller;

import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.reports.model.entities.DailyReportInfo;
import com.tesda8.region8.reports.service.DailyReportInfoService;
import com.tesda8.region8.util.service.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController extends HeaderController {

    private DailyReportInfoService dailyReportInfoService;

    @Autowired
    public HomeController(DailyReportInfoService dailyReportInfoService,
                          RegisteredProgramStatusService registeredProgramStatusService,
                          ExpiredCertificateService expiredCertificateService) {
        super(registeredProgramStatusService, expiredCertificateService);
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
        model.addAttribute("updatedDate", dailyReportInfo.getUpdatedDate().plusHours(8));
        model.addAttribute("updatedBy", dailyReportInfo.getUpdatedBy());
        model.addAttribute("dateTimeNow", ApplicationUtil.getLocalDateTimeNow());
        return "dashboard/daily_reports";
    }

    @GetMapping("/dashboard/daily-po")
    public String dailyPo(Model model) {
        addStatusCounterToModel(model);
        return "dashboard/daily_po_report";
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
