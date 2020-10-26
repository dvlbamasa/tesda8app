package com.tesda8.region8.web.controller;

import com.tesda8.region8.web.model.dto.wrapper.CertificationRateReportWrapper;
import com.tesda8.region8.web.model.dto.wrapper.GeneralReportsDtoWrapper;
import com.tesda8.region8.web.model.dto.wrapper.ROPerModeReportWrapper;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.web.service.CertificationRateReportService;
import com.tesda8.region8.web.service.GeneralReportService;
import com.tesda8.region8.web.service.ROPerModeReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class DailyReportController {

    private static Logger logger = LoggerFactory.getLogger(DailyReportController.class);

    private GeneralReportService generalReportService;
    private CertificationRateReportService certificationRateReportService;
    private ROPerModeReportService roPerModeReportService;

    @Autowired
    public DailyReportController(GeneralReportService generalReportService,
                                 CertificationRateReportService certificationRateReportService,
                                 ROPerModeReportService roPerModeReportService) {
        this.generalReportService = generalReportService;
        this.certificationRateReportService = certificationRateReportService;
        this.roPerModeReportService = roPerModeReportService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dailyReports")
    public String showPOReports(Model model) {

        GeneralReportsDtoWrapper generalReportsDtoWrapper = new GeneralReportsDtoWrapper();
        CertificationRateReportWrapper certificationRateReportWrapper = new CertificationRateReportWrapper();
        ROPerModeReportWrapper roPerModeReportWrapper = new ROPerModeReportWrapper();


        generalReportsDtoWrapper.setPoReports(generalReportService.
                findAllGeneralReportByDailyReportType(DailyReportType.PO_REPORT));

        certificationRateReportWrapper.setCertificationRateReportDtos(
                certificationRateReportService.findAllCertificationRate());

        roPerModeReportWrapper.setRoPerModeGSReports(
                roPerModeReportService.findAllROPerModeReportsByReportSourceType(ReportSourceType.GS));

        roPerModeReportWrapper.setRoPerModeT2Reports(
                roPerModeReportService.findAllROPerModeReportsByReportSourceType(ReportSourceType.T2MIS));

        generalReportsDtoWrapper.setInstitutionBasedReports(
                generalReportService.findAllGeneralReportByDailyReportType(DailyReportType.INSTITUTION_BASED_REPORT));

        generalReportsDtoWrapper.setEnterpriseBasedGSReports(
                generalReportService.findAllGeneralReportByDailyReportTypeAndReportSourceType(
                        DailyReportType.ENTERPRISE_BASED_REPORT, ReportSourceType.GS));

        generalReportsDtoWrapper.setEnterpriseBasedT2Reports(
                generalReportService.findAllGeneralReportByDailyReportTypeAndReportSourceType(
                DailyReportType.ENTERPRISE_BASED_REPORT, ReportSourceType.T2MIS));

        generalReportsDtoWrapper.setCommunityBasedReports(
                generalReportService.findAllGeneralReportByDailyReportType(DailyReportType.COMMUNITY_BASED_REPORT));

        generalReportsDtoWrapper.setTtiReports(
                generalReportService.findAllGeneralReportByDailyReportType(DailyReportType.TTI_REPORT));

        model.addAttribute("reports", generalReportsDtoWrapper);
        model.addAttribute("certificationReports", certificationRateReportWrapper);
        model.addAttribute("roPerModeReports", roPerModeReportWrapper);

        return "daily_reports/update_reports";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dailyReports/{dailyReportType}/type/{reportSourceType}/source/update")
    public String updateGeneralReports(
            @ModelAttribute GeneralReportsDtoWrapper generalReportsDtoWrapper,
            @PathVariable("dailyReportType") DailyReportType dailyReportType,
            @PathVariable("reportSourceType") ReportSourceType reportSourceType,
            BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        switch (dailyReportType) {
            case PO_REPORT:
                generalReportService.updateGeneralReports(generalReportsDtoWrapper.getPoReports());
                break;
            case INSTITUTION_BASED_REPORT:
                generalReportService.updateGeneralReports(generalReportsDtoWrapper.getInstitutionBasedReports());
                break;
            case TTI_REPORT:
                generalReportService.updateGeneralReports(generalReportsDtoWrapper.getTtiReports());
                break;
            case COMMUNITY_BASED_REPORT:
                generalReportService.updateGeneralReports(generalReportsDtoWrapper.getCommunityBasedReports());
                break;
            case ENTERPRISE_BASED_REPORT:
                if (reportSourceType == ReportSourceType.GS) {
                    generalReportService.updateGeneralReports(generalReportsDtoWrapper.getEnterpriseBasedGSReports());
                } else {
                    generalReportService.updateGeneralReports(generalReportsDtoWrapper.getEnterpriseBasedT2Reports());
                }
                break;
            default:
                break;
        }
        return "redirect:/dashboard";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dailyReports/certificationRate/update")
    public String updateCertificationRateReports(
            @ModelAttribute CertificationRateReportWrapper certificationRateReportWrapper,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        certificationRateReportService.updateCertificationRateReports(certificationRateReportWrapper.getCertificationRateReportDtos());
        return "redirect:/dashboard";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dailyReports/roPerMode/{reportSourceType}/type/update")
    public String updateRoPerModeReports(
            @ModelAttribute ROPerModeReportWrapper roPerModeReportWrapper,
            @PathVariable("reportSourceType") ReportSourceType reportSourceType,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }

        if (reportSourceType == ReportSourceType.GS) {
            roPerModeReportService.updateROPerModeReports(roPerModeReportWrapper.getRoPerModeGSReports());
        } else {
            roPerModeReportService.updateROPerModeReports(roPerModeReportWrapper.getRoPerModeT2Reports());
        }
        return "redirect:/dashboard";
    }
}
