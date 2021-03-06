package com.tesda8.region8.web.restcontroller.reports;

import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.reports.service.MonthlyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/monthlyReport")
public class MonthlyReportRestController {

    private MonthlyReportService monthlyReportService;

    @Autowired
    public MonthlyReportRestController(MonthlyReportService monthlyReportService) {
        this.monthlyReportService = monthlyReportService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{month}/month/{year}/year/update")
    public void updateMonthlyReport(@PathVariable("month") Month month,
                                    @PathVariable("year") String year,
                                    @RequestBody List<GeneralReportDto> generalReportDtoList) {
        monthlyReportService.updateMonthlyReport(generalReportDtoList, month, Integer.parseInt(year));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{month}/month/{year}/year/delete")
    public void deleteMonthlyReport(@PathVariable("month")Month month,
                                    @PathVariable("year") String year) {
        monthlyReportService.deleteMonthlyReport(month, Integer.parseInt(year));
    }

}
