package com.tesda8.region8.web.service.impl;

import com.tesda8.region8.util.service.ReportUtil;
import com.tesda8.region8.web.model.dto.CertificationRateReportDto;
import com.tesda8.region8.web.model.dto.EgacDataDto;
import com.tesda8.region8.web.model.dto.GeneralReportDto;
import com.tesda8.region8.web.model.dto.ROPerModeReportDto;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.DeliveryMode;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.web.service.CertificationRateReportService;
import com.tesda8.region8.web.service.GeneralReportService;
import com.tesda8.region8.web.service.ROPerModeReportService;
import com.tesda8.region8.web.service.TableDataFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableDataFetcherServiceImpl implements TableDataFetcherService {

    private CertificationRateReportService certificationRateReportService;
    private ROPerModeReportService roPerModeReportService;
    private GeneralReportService generalReportService;

    @Autowired
    public TableDataFetcherServiceImpl(CertificationRateReportService certificationRateReportService,
                                       ROPerModeReportService roPerModeReportService,
                                       GeneralReportService generalReportService) {
        this.certificationRateReportService = certificationRateReportService;
        this.roPerModeReportService = roPerModeReportService;
        this.generalReportService = generalReportService;
    }


    @Override
    public List<GeneralReportDto> fetchGeneralReportTableData(DailyReportType dailyReportType, ReportSourceType reportSourceType, EgacType egacType) {
        List<GeneralReportDto> generalReportDtos =
                generalReportService.findAllGeneralReportByDailyReportTypeAndReportSourceTypeAndEgacType(
                        dailyReportType, reportSourceType, egacType);
        GeneralReportDto total = new GeneralReportDto();
        total.setEgacDataDto(new EgacDataDto());
        generalReportDtos.forEach(
                generalReportDto -> {
                    total.getEgacDataDto().setTarget(generalReportDto.getEgacDataDto().getTarget() + total.getEgacDataDto().getTarget());
                    total.getEgacDataDto().setOutput(generalReportDto.getEgacDataDto().getOutput() + total.getEgacDataDto().getOutput());
                }
        );
        total.setOperatingUnitType(OperatingUnitType.TOTAL);
        total.getEgacDataDto().setEgacType(egacType);
        total.getEgacDataDto().setRate(ReportUtil.calculateRate(total.getEgacDataDto().getTarget(), total.getEgacDataDto().getOutput()));
        generalReportDtos.add(total);
        return generalReportDtos;
    }

    @Override
    public List<CertificationRateReportDto> fetchCertificationRateTableData() {
        List<CertificationRateReportDto> certificationRateReportDtos =
                certificationRateReportService.findAllCertificationRate();
        CertificationRateReportDto total = new CertificationRateReportDto();
        certificationRateReportDtos.forEach(
                certificationRateReportDto -> {
                    total.setAssessed(certificationRateReportDto.getAssessed() + total.getAssessed());
                    total.setCertified(certificationRateReportDto.getCertified() + total.getCertified());
                }
        );
        total.setOperatingUnitType(OperatingUnitType.TOTAL);
        total.setRate(ReportUtil.calculateRate(total.getAssessed(), total.getCertified()));
        certificationRateReportDtos.add(total);
        return certificationRateReportDtos;
    }

    @Override
    public List<ROPerModeReportDto> fetchROPerModeTableData(ReportSourceType reportSourceType, EgacType egacType) {
        List<ROPerModeReportDto> roPerModeReportDtos =
                roPerModeReportService.findAllROPerModeReportsByEgacTypeAndReportSourceType(egacType, reportSourceType);
        ROPerModeReportDto total = new ROPerModeReportDto();
        total.setEgacDataDto(new EgacDataDto());
        roPerModeReportDtos.forEach(
                roPerModeReportDto -> {
                    total.getEgacDataDto().setOutput(roPerModeReportDto.getEgacDataDto().getOutput() + total.getEgacDataDto().getOutput());
                    total.getEgacDataDto().setTarget(roPerModeReportDto.getEgacDataDto().getTarget() + total.getEgacDataDto().getTarget());
                }
        );
        total.setDeliveryMode(DeliveryMode.TOTAL);
        total.getEgacDataDto().setEgacType(egacType);
        total.getEgacDataDto().setRate(ReportUtil.calculateRate(total.getEgacDataDto().getTarget(), total.getEgacDataDto().getOutput()));
        roPerModeReportDtos.add(total);
        return roPerModeReportDtos;
    }
}
