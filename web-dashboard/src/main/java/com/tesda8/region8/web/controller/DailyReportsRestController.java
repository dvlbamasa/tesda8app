package com.tesda8.region8.web.controller;

import com.tesda8.region8.web.model.dto.CertificationRateReportDto;
import com.tesda8.region8.web.model.dto.GeneralReportDto;
import com.tesda8.region8.web.model.dto.ROPerModeReportDto;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.web.service.CertificationRateReportService;
import com.tesda8.region8.web.service.GeneralReportService;
import com.tesda8.region8.web.service.ROPerModeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class DailyReportsRestController {

    private GeneralReportService generalReportService;
    private CertificationRateReportService certificationRateReportService;
    private ROPerModeReportService roPerModeReportService;

    @Autowired
    public DailyReportsRestController(GeneralReportService generalReportService,
                                      CertificationRateReportService certificationRateReportService,
                                      ROPerModeReportService roPerModeReportService) {
        this.generalReportService = generalReportService;
        this.certificationRateReportService = certificationRateReportService;
        this.roPerModeReportService = roPerModeReportService;
    }

    @GetMapping("/generalReports")
    public List<GeneralReportDto> getReports() {
        return generalReportService.findAllGeneralReport();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/generalReports/{dailyReport}")
    public List<GeneralReportDto> getReportsByDailyReportType(@PathVariable("dailyReport") DailyReportType dailyReportType) {
        return generalReportService.findAllGeneralReportByDailyReportType(dailyReportType);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/generalReports/{dailyReport}/dailyReportType/{egacType}/egacType")
    public List<GeneralReportDto> getReportsByDailyReportType(@PathVariable("dailyReport") DailyReportType dailyReportType,
                                                              @PathVariable("egacType") EgacType egacType) {
        return generalReportService.findAllGeneralReportByEgacTypeAndDailyReportType(egacType, dailyReportType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/generalReports/{dailyReport}/dailyReportType/{reportSourceType}/reportSource")
    public List<GeneralReportDto> getReportsByDailyReportTypeAndReportSourceType(@PathVariable("dailyReport") DailyReportType dailyReportType,
                                                              @PathVariable("reportSourceType") ReportSourceType reportSourceType) {
        return generalReportService.findAllGeneralReportByDailyReportTypeAndReportSourceType(dailyReportType, reportSourceType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/generalReports/{dailyReport}/dailyReportType/{reportSourceType}/reportSource/{egacType}/egacType")
    public List<GeneralReportDto> getReportsByDailyReportTypeAndReportSourceTypeAndEgacType(@PathVariable("dailyReport") DailyReportType dailyReportType,
                                                                                 @PathVariable("reportSourceType")ReportSourceType reportSourceType,
                                                                                @PathVariable("egacType") EgacType egacType) {
        return generalReportService.findAllGeneralReportByDailyReportTypeAndReportSourceTypeAndEgacType(dailyReportType, reportSourceType, egacType);
    }

    @GetMapping("/certificationRateReports")
    public List<CertificationRateReportDto> getCertificationRateReports() {
        return certificationRateReportService.findAllCertificationRate();
    }

    @GetMapping("/roPerModeReports")
    public List<ROPerModeReportDto> getROPerModeReports() {
        return roPerModeReportService.findAllROPerModeReports();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roPerModeReports/{egacType}/egacType/{reportSource}/reportSource")
    public List<ROPerModeReportDto> getROPerModeReportsByEgacTypeAndReportSourceType(@PathVariable("egacType") EgacType egacType,
                                                                                     @PathVariable("reportSource")ReportSourceType reportSourceType) {
        return roPerModeReportService.findAllROPerModeReportsByEgacTypeAndReportSourceType(egacType, reportSourceType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roPerModeReports/{reportSource}/reportSource")
    public List<ROPerModeReportDto> getROPerModeReportsByReportSourceType(@PathVariable("reportSource")ReportSourceType reportSourceType) {
        return roPerModeReportService.findAllROPerModeReportsByReportSourceType(reportSourceType);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/generalReports/update")
    @ResponseBody
    public ResponseEntity<List<GeneralReportDto>> updateGeneralReports(@RequestBody List<GeneralReportDto> generalReportDtos) {
        return new ResponseEntity<>(generalReportService.updateGeneralReports(generalReportDtos), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/certificationRateReports/update")
    @ResponseBody
    public ResponseEntity<List<CertificationRateReportDto>> updateCertificationRateReports(
            @RequestBody List<CertificationRateReportDto> certificationRateReportDtos) {
        return new ResponseEntity<>(
                certificationRateReportService.updateCertificationRateReports(certificationRateReportDtos),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/roPerModeReports/update")
    @ResponseBody
    public ResponseEntity<List<ROPerModeReportDto>> updateROPerModeReports(
            @RequestBody List<ROPerModeReportDto> roPerModeReportDtos) {
        return new ResponseEntity<>(roPerModeReportService.updateROPerModeReports(roPerModeReportDtos), HttpStatus.OK);
    }
}
