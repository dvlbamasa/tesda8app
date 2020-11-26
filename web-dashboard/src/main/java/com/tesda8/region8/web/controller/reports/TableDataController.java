package com.tesda8.region8.web.controller.reports;

import com.tesda8.region8.web.model.dto.wrapper.CertificationRateReportWrapper;
import com.tesda8.region8.web.model.dto.wrapper.GeneralReportsDtoWrapper;
import com.tesda8.region8.web.model.dto.wrapper.ROPerModeReportWrapper;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.reports.service.TableDataFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TableDataController {

    private TableDataFetcherService tableDataFetcherService;

    @Autowired
    public TableDataController(TableDataFetcherService tableDataFetcherService) {
        this.tableDataFetcherService = tableDataFetcherService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tableData")
    public String showTableData(Model model) {

        GeneralReportsDtoWrapper generalReportsDtoWrapper = new GeneralReportsDtoWrapper();
        CertificationRateReportWrapper certificationRateReportWrapper = new CertificationRateReportWrapper();
        ROPerModeReportWrapper roPerModeReportWrapper = new ROPerModeReportWrapper();

        certificationRateReportWrapper.setCertificationRateReportDtos(tableDataFetcherService.fetchCertificationRateTableData());

        generalReportsDtoWrapper.setPoReports(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.PO_REPORT, ReportSourceType.T2MIS, EgacType.ENROLLED));
        generalReportsDtoWrapper.getPoReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.PO_REPORT, ReportSourceType.T2MIS, EgacType.GRADUATED));
        generalReportsDtoWrapper.getPoReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.PO_REPORT, ReportSourceType.T2MIS, EgacType.ASSESSED));
        generalReportsDtoWrapper.getPoReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.PO_REPORT, ReportSourceType.T2MIS, EgacType.CERTIFIED));

        generalReportsDtoWrapper.setCommunityBasedReports(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.COMMUNITY_BASED_REPORT, ReportSourceType.T2MIS, EgacType.ENROLLED));
        generalReportsDtoWrapper.getCommunityBasedReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.COMMUNITY_BASED_REPORT, ReportSourceType.T2MIS, EgacType.GRADUATED));

        generalReportsDtoWrapper.setInstitutionBasedReports(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.INSTITUTION_BASED_REPORT, ReportSourceType.T2MIS, EgacType.ENROLLED));
        generalReportsDtoWrapper.getInstitutionBasedReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.INSTITUTION_BASED_REPORT, ReportSourceType.T2MIS, EgacType.GRADUATED));

        generalReportsDtoWrapper.setEnterpriseBasedT2Reports(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.ENTERPRISE_BASED_REPORT, ReportSourceType.T2MIS, EgacType.ENROLLED));
        generalReportsDtoWrapper.getEnterpriseBasedT2Reports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.ENTERPRISE_BASED_REPORT, ReportSourceType.T2MIS, EgacType.GRADUATED));

        generalReportsDtoWrapper.setEnterpriseBasedGSReports(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.ENTERPRISE_BASED_REPORT, ReportSourceType.GS, EgacType.ENROLLED));
        generalReportsDtoWrapper.getEnterpriseBasedGSReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.ENTERPRISE_BASED_REPORT, ReportSourceType.GS, EgacType.GRADUATED));

        generalReportsDtoWrapper.setTtiReports(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.TTI_REPORT, ReportSourceType.T2MIS, EgacType.ENROLLED));
        generalReportsDtoWrapper.getTtiReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.TTI_REPORT, ReportSourceType.T2MIS, EgacType.GRADUATED));
        generalReportsDtoWrapper.getTtiReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.TTI_REPORT, ReportSourceType.T2MIS, EgacType.ASSESSED));
        generalReportsDtoWrapper.getTtiReports().addAll(tableDataFetcherService.fetchGeneralReportTableData(DailyReportType.TTI_REPORT, ReportSourceType.T2MIS, EgacType.CERTIFIED));

        generalReportsDtoWrapper.setTtiReportsAC(tableDataFetcherService.fetchTTIReportTableData(EgacType.ASSESSED));
        generalReportsDtoWrapper.getTtiReportsAC().addAll(tableDataFetcherService.fetchTTIReportTableData(EgacType.CERTIFIED));

        roPerModeReportWrapper.setRoPerModeT2Reports(tableDataFetcherService.fetchROPerModeTableData(ReportSourceType.T2MIS, EgacType.ENROLLED));
        roPerModeReportWrapper.getRoPerModeT2Reports().addAll(tableDataFetcherService.fetchROPerModeTableData(ReportSourceType.T2MIS, EgacType.GRADUATED));

        roPerModeReportWrapper.setRoPerModeGSReports(tableDataFetcherService.fetchROPerModeTableData(ReportSourceType.GS, EgacType.ENROLLED));
        roPerModeReportWrapper.getRoPerModeGSReports().addAll(tableDataFetcherService.fetchROPerModeTableData(ReportSourceType.GS, EgacType.GRADUATED));

        model.addAttribute("reports", generalReportsDtoWrapper);
        model.addAttribute("certificationReports", certificationRateReportWrapper);
        model.addAttribute("roPerModeReports", roPerModeReportWrapper);

        return "daily_reports/table_data";
    }
}
