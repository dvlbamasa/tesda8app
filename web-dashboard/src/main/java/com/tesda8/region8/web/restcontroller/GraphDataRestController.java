package com.tesda8.region8.web.restcontroller;

import com.tesda8.region8.util.enums.ScholarshipType;
import com.tesda8.region8.util.model.DataPoints;
import com.tesda8.region8.web.model.dto.graph.GraphDataList;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.DataPointType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.web.service.GraphDataFetcherService;
import com.tesda8.region8.web.service.OPCRGraphDataFetcherService;
import com.tesda8.region8.web.service.ScholarshipGraphDataFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ServiceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/graph")
@RequiredArgsConstructor
public class GraphDataRestController {

    private final GraphDataFetcherService graphDataFetcherService;
    private final OPCRGraphDataFetcherService opcrGraphDataFetcherService;
    private final ScholarshipGraphDataFetcherService scholarshipGraphDataFetcherService;

    @RequestMapping(method = RequestMethod.GET, value = "/certificationRate/{dataPointType}/dataPointType")
    @ResponseBody
    public List<DataPoints> fetchCertificationRateDataPoints(@PathVariable("dataPointType") DataPointType dataPointType) {
        return graphDataFetcherService.fetchCertificationRateData(dataPointType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roPerMode/{dataPointType}/dataPointType/{egacType}/egacType/{reportSourceType}/reportSource")
    @ResponseBody
    public List<DataPoints> fetchROPerModeDataPoints(@PathVariable("dataPointType") DataPointType dataPointType,
                                                     @PathVariable("egacType") EgacType egacType,
                                                     @PathVariable("reportSourceType") ReportSourceType reportSourceType) {
        return graphDataFetcherService.fetchROPerModeReportsData(dataPointType, egacType, reportSourceType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/generalReport/{dataPointType}/dataPointType/{egacType}/egacType/{reportSourceType}/reportSource/{dailyReportType}/reportType")
    @ResponseBody
    public List<DataPoints> fetchGeneralReportDataPoints(@PathVariable("dataPointType") DataPointType dataPointType,
                                                         @PathVariable("egacType")EgacType egacType,
                                                         @PathVariable("reportSourceType")ReportSourceType reportSourceType,
                                                         @PathVariable("dailyReportType") DailyReportType dailyReportType) {
        return graphDataFetcherService.fetchGeneralReportsData(dataPointType, egacType, reportSourceType, dailyReportType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certificationRate")
    @ResponseBody
    public GraphDataList fetchCertificationRateDataList() {
        return graphDataFetcherService.fetchCertificationRateDataList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roPerMode/{egacType}/egacType/{reportSourceType}/reportSource")
    @ResponseBody
    public GraphDataList fetchROPerModeDataList(@PathVariable("egacType")EgacType egacType,
                                                @PathVariable("reportSourceType")ReportSourceType reportSourceType) {
        return graphDataFetcherService.fetchROPerModeReportsDataList(egacType, reportSourceType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/generalReport/{egacType}/egacType/{reportSourceType}/reportSource/{dailyReportType}/reportType")
    @ResponseBody
    public GraphDataList fetchGeneralDataList(@PathVariable("egacType")EgacType egacType,
                                                    @PathVariable("reportSourceType")ReportSourceType reportSourceType,
                                                    @PathVariable("dailyReportType")DailyReportType dailyReportType) {
        return graphDataFetcherService.fetchGeneralDataList(egacType, reportSourceType, dailyReportType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ttiReport/{egacType}/egacType")
    @ResponseBody
    public GraphDataList fetchTTIReportDataList(@PathVariable("egacType") EgacType egacType) {
        return graphDataFetcherService.fetchTTIReportDataList(egacType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/monthlyReports/{operatingUnitType}/operatingUnitType/{egacType}/egacType")
    @ResponseBody
    public GraphDataList fetchMonthlyReportDataList(@PathVariable("operatingUnitType") OperatingUnitType operatingUnitType,
                                                    @PathVariable("egacType")EgacType egacType) {
        return graphDataFetcherService.fetchMonthlyReportDataList(egacType, operatingUnitType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/successIndicator/{id}/dataPoints")
    @ResponseBody
    public GraphDataList fetchOPCRDataList(@PathVariable("id") Long id) {
        return opcrGraphDataFetcherService.fetchOPCRDataList(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/scholarship/graphData")
    @ResponseBody
    public GraphDataList fetchScholarshipDataList(@RequestParam("year") Long year,
                                                  @RequestParam("egacType") EgacType egacType,
                                                  @RequestParam("operatingUnit") OperatingUnitType operatingUnitType,
                                                  @RequestParam("scholarshipType")ScholarshipType scholarshipType) throws ServiceNotFoundException {
        return scholarshipGraphDataFetcherService.fetchMonthlyGraphDataList(year, egacType, operatingUnitType, scholarshipType);
    }
}