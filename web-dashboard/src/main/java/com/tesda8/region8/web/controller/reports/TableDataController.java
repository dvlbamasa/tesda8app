package com.tesda8.region8.web.controller.reports;

import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.web.controller.HeaderController;
import com.tesda8.region8.web.model.dto.wrapper.GeneralReportTableWrapper;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.reports.service.TableDataFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TableDataController extends HeaderController {

    private TableDataFetcherService tableDataFetcherService;

    @Autowired
    public TableDataController(TableDataFetcherService tableDataFetcherService,
                               RegisteredProgramStatusService registeredProgramStatusService,
                               ExpiredCertificateService expiredCertificateService) {
        super(registeredProgramStatusService, expiredCertificateService);
        this.tableDataFetcherService = tableDataFetcherService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tableData")
    public String showTableData(Model model) {
        GeneralReportTableWrapper generalReportTableWrapper = new GeneralReportTableWrapper();

        generalReportTableWrapper.setCertificationRateReports(tableDataFetcherService.fetchCertificationRateTableData());
        generalReportTableWrapper.setPoReports(tableDataFetcherService.fetchPOReports());
        generalReportTableWrapper.setTtiReports(tableDataFetcherService.fetchTTIReports());
        generalReportTableWrapper.setRoPerModeT2Reports(tableDataFetcherService.fetchRoPerModeReports(ReportSourceType.T2MIS));
        generalReportTableWrapper.setRoPerModeGSReports(tableDataFetcherService.fetchRoPerModeReports(ReportSourceType.GS));
        generalReportTableWrapper.setCommunityBasedReports(tableDataFetcherService.fetchEGReports(DailyReportType.COMMUNITY_BASED_REPORT, ReportSourceType.T2MIS));
        generalReportTableWrapper.setInstitutionBasedReports(tableDataFetcherService.fetchEGReports(DailyReportType.INSTITUTION_BASED_REPORT, ReportSourceType.T2MIS));
        generalReportTableWrapper.setEnterpriseBasedGSReports(tableDataFetcherService.fetchEGReports(DailyReportType.ENTERPRISE_BASED_REPORT, ReportSourceType.GS));
        generalReportTableWrapper.setEnterpriseBasedT2Reports(tableDataFetcherService.fetchEGReports(DailyReportType.ENTERPRISE_BASED_REPORT, ReportSourceType.T2MIS));

        model.addAttribute("reports", generalReportTableWrapper);
        addStatusCounterToModel(model);

        return "daily_reports/table_data";
    }
}
