package com.tesda8.region8.web.restcontroller.reports;

import com.tesda8.region8.reports.model.dto.CertificationRateReportDto;
import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.reports.model.dto.ROPerModeReportDto;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.reports.service.TableDataFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/table")
public class TableDataRestController {

    private TableDataFetcherService tableDataFetcherService;

    @Autowired
    public TableDataRestController(TableDataFetcherService tableDataFetcherService) {
        this.tableDataFetcherService = tableDataFetcherService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reports/{dailyReportType}/reportType/{reportSourceType}/reportSource/{egacType}/egacType")
    public List<GeneralReportDto> showReportsTable(@PathVariable("dailyReportType") DailyReportType dailyReportType,
                                                   @PathVariable("reportSourceType") ReportSourceType reportSourceType,
                                                   @PathVariable("egacType") EgacType egacType) {
        return tableDataFetcherService.fetchGeneralReportTableData(dailyReportType, reportSourceType, egacType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certificationRate")
    public List<CertificationRateReportDto> getCertificationRateTable() {
        return tableDataFetcherService.fetchCertificationRateTableData();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roPerMode/{reportSourceType}/reportSource/{egacType}/egacType")
    public List<ROPerModeReportDto> getROPerModeTableData(@PathVariable("reportSourceType")ReportSourceType reportSourceType,
                                                          @PathVariable("egacType")EgacType egacType) {
        return tableDataFetcherService.fetchROPerModeTableData(reportSourceType, egacType);
    }
}
