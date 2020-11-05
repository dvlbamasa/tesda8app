package com.tesda8.region8.web.controller;

import com.tesda8.region8.reports.model.entities.DailyReportInfo;
import com.tesda8.region8.reports.service.DailyReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    private DailyReportInfoService dailyReportInfoService;

    @Autowired
    public HomeController(DailyReportInfoService dailyReportInfoService) {
        this.dailyReportInfoService = dailyReportInfoService;
    }

    @GetMapping("/")
    public String home1() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String daily(Model model) {
        DailyReportInfo dailyReportInfo = dailyReportInfoService.getLatestDailyReportInfo();
        model.addAttribute("dailyReportInfo", dailyReportInfo.getUpdatedDate().plusHours(8));
        model.addAttribute("dateTimeNow", LocalDateTime.now().plusHours(8));
        return "daily_reports/dashboard";
    }

    @GetMapping("/monthly")
    public String monthly() {
        return "daily_reports/monthly_reports";
    }

    @GetMapping("/program_registration")
    public String programRegistration() {
        return "program_registration/program_registration";
    }

    @GetMapping("/certification")
    public String certification() {
        return "certification/certification";
    }

    @GetMapping("/scholarship")
    public String scholarship() {
        return "scholarship/scholarship";
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
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }
}
