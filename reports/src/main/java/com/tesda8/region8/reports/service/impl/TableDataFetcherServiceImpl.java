package com.tesda8.region8.reports.service.impl;

import com.google.common.collect.Lists;
import com.tesda8.region8.reports.model.wrapper.GeneralReportDataRowEGWrapper;
import com.tesda8.region8.reports.model.wrapper.GeneralReportDataRowWrapper;
import com.tesda8.region8.reports.model.wrapper.RoPerModeDataRowWrapper;
import com.tesda8.region8.reports.model.wrapper.TTIReportDataRowWrapper;
import com.tesda8.region8.util.enums.TTIType;
import com.tesda8.region8.util.service.ReportUtil;
import com.tesda8.region8.reports.model.dto.CertificationRateReportDto;
import com.tesda8.region8.reports.model.dto.EgacDataDto;
import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.reports.model.dto.ROPerModeReportDto;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.DeliveryMode;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.reports.model.dto.TTIReportDto;
import com.tesda8.region8.reports.service.CertificationRateReportService;
import com.tesda8.region8.reports.service.GeneralReportService;
import com.tesda8.region8.reports.service.ROPerModeReportService;
import com.tesda8.region8.reports.service.TTIReportService;
import com.tesda8.region8.reports.service.TableDataFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TableDataFetcherServiceImpl implements TableDataFetcherService {

    private CertificationRateReportService certificationRateReportService;
    private ROPerModeReportService roPerModeReportService;
    private GeneralReportService generalReportService;
    private TTIReportService ttiReportService;

    @Autowired
    public TableDataFetcherServiceImpl(CertificationRateReportService certificationRateReportService,
                                       ROPerModeReportService roPerModeReportService,
                                       GeneralReportService generalReportService,
                                       TTIReportService ttiReportService) {
        this.certificationRateReportService = certificationRateReportService;
        this.roPerModeReportService = roPerModeReportService;
        this.generalReportService = generalReportService;
        this.ttiReportService = ttiReportService;
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

    @Override
    public List<TTIReportDto> fetchTTIReportTableData(EgacType egacType) {
        List<TTIReportDto> ttiReports = ttiReportService.getAllTTIReportByEgacType(egacType);
        TTIReportDto total = new TTIReportDto();
        total.setEgacDataDto(new EgacDataDto());
        ttiReports.forEach(
                ttiReportDto -> {
                    total.getEgacDataDto().setOutput(ttiReportDto.getEgacDataDto().getOutput() + total.getEgacDataDto().getOutput());
                    total.getEgacDataDto().setTarget(ttiReportDto.getEgacDataDto().getTarget() + total.getEgacDataDto().getTarget());
                }
        );
        total.setTtiType(TTIType.TOTAL);
        total.getEgacDataDto().setEgacType(egacType);
        total.getEgacDataDto().setRate(ReportUtil.calculateRate(total.getEgacDataDto().getTarget(), total.getEgacDataDto().getOutput()));
        ttiReports.add(total);
        return ttiReports;
    }

    @Override
    public List<GeneralReportDataRowWrapper> fetchPOReports() {
        List<GeneralReportDataRowWrapper> generalReportDataRowWrapperList = Lists.newArrayList();
        List<GeneralReportDto> enrolledPo = fetchGeneralReportTableData(DailyReportType.PO_REPORT, ReportSourceType.T2MIS, EgacType.ENROLLED);
        List<GeneralReportDto> graduatesPo = fetchGeneralReportTableData(DailyReportType.PO_REPORT, ReportSourceType.T2MIS, EgacType.GRADUATED);
        List<GeneralReportDto> assessedPo = fetchGeneralReportTableData(DailyReportType.PO_REPORT, ReportSourceType.T2MIS, EgacType.ASSESSED);
        List<GeneralReportDto> certifiedPo = fetchGeneralReportTableData(DailyReportType.PO_REPORT, ReportSourceType.T2MIS, EgacType.CERTIFIED);

        Arrays.asList(OperatingUnitType.values()).forEach(
                operatingUnitType -> {
                    GeneralReportDataRowWrapper generalReportDataRowWrapper = new GeneralReportDataRowWrapper();
                    generalReportDataRowWrapper.setOperatingUnitType(operatingUnitType);
                    generalReportDataRowWrapper.setEnrolled(enrolledPo
                            .stream()
                            .filter(generalReportDto -> generalReportDto.getOperatingUnitType().equals(operatingUnitType))
                            .findFirst().get());
                    generalReportDataRowWrapper.setAssessed(assessedPo
                            .stream()
                            .filter(generalReportDto -> generalReportDto.getOperatingUnitType().equals(operatingUnitType))
                            .findFirst().get());
                    generalReportDataRowWrapper.setGraduates(graduatesPo
                            .stream()
                            .filter(generalReportDto -> generalReportDto.getOperatingUnitType().equals(operatingUnitType))
                            .findFirst().get());
                    generalReportDataRowWrapper.setCertified(certifiedPo
                            .stream()
                            .filter(generalReportDto -> generalReportDto.getOperatingUnitType().equals(operatingUnitType))
                            .findFirst().get());
                    generalReportDataRowWrapperList.add(generalReportDataRowWrapper);
                }
        );
        return generalReportDataRowWrapperList;
    }

    @Override
    public List<GeneralReportDataRowEGWrapper> fetchEGReports(DailyReportType dailyReportType, ReportSourceType reportSourceType) {
        List<GeneralReportDataRowEGWrapper> generalReportDataRowEGWrapperList = Lists.newArrayList();
        List<GeneralReportDto> enrolled = fetchGeneralReportTableData(dailyReportType, reportSourceType, EgacType.ENROLLED);
        List<GeneralReportDto> graduates = fetchGeneralReportTableData(dailyReportType, reportSourceType, EgacType.GRADUATED);
        Arrays.asList(OperatingUnitType.values()).forEach(
                operatingUnitType -> {
                    GeneralReportDataRowEGWrapper generalReportDataRowEGWrapper = new GeneralReportDataRowEGWrapper();
                    generalReportDataRowEGWrapper.setOperatingUnitType(operatingUnitType);
                    generalReportDataRowEGWrapper.setEnrolled(enrolled
                            .stream()
                            .filter(generalReportDto -> generalReportDto.getOperatingUnitType().equals(operatingUnitType))
                            .findFirst().get());
                    generalReportDataRowEGWrapper.setGraduates(graduates
                            .stream()
                            .filter(generalReportDto -> generalReportDto.getOperatingUnitType().equals(operatingUnitType))
                            .findFirst().get());
                    generalReportDataRowEGWrapperList.add(generalReportDataRowEGWrapper);
                }
        );
        return generalReportDataRowEGWrapperList;
    }

    @Override
    public List<RoPerModeDataRowWrapper> fetchRoPerModeReports(ReportSourceType reportSourceType) {
        List<RoPerModeDataRowWrapper> roPerModeDataRowWrapperList = Lists.newArrayList();
        List<ROPerModeReportDto> enrolled = fetchROPerModeTableData(reportSourceType, EgacType.ENROLLED);
        List<ROPerModeReportDto> graduates = fetchROPerModeTableData(reportSourceType, EgacType.GRADUATED);
        Arrays.asList(DeliveryMode.values()).forEach(
                deliveryMode -> {
                    RoPerModeDataRowWrapper roPerModeDataRowWrapper = new RoPerModeDataRowWrapper();
                    roPerModeDataRowWrapper.setDeliveryMode(deliveryMode);
                    roPerModeDataRowWrapper.setEnrolled(enrolled
                            .stream()
                            .filter(roPerModeReportDto -> roPerModeReportDto.getDeliveryMode().equals(deliveryMode))
                            .findAny().get());
                    roPerModeDataRowWrapper.setGraduates(graduates
                            .stream()
                            .filter(roPerModeReportDto -> roPerModeReportDto.getDeliveryMode().equals(deliveryMode))
                            .findAny().get());
                    roPerModeDataRowWrapperList.add(roPerModeDataRowWrapper);
                }
        );
        return roPerModeDataRowWrapperList;
    }

    @Override
    public List<TTIReportDataRowWrapper> fetchTTIReports() {
        List<TTIReportDataRowWrapper> ttiReportDataRowWrapperList = Lists.newArrayList();
        List<TTIReportDto> enrolled = fetchTTIReportTableData(EgacType.ENROLLED);
        List<TTIReportDto> graduates = fetchTTIReportTableData(EgacType.GRADUATED);
        List<TTIReportDto> assessed = fetchTTIReportTableData(EgacType.ASSESSED);
        List<TTIReportDto> certified = fetchTTIReportTableData(EgacType.CERTIFIED);
        Arrays.asList(TTIType.values()).forEach(
                ttiType -> {
                    TTIReportDataRowWrapper ttiReportDataRowWrapper = new TTIReportDataRowWrapper();
                    ttiReportDataRowWrapper.setTtiType(ttiType);
                    ttiReportDataRowWrapper.setEnrolled(enrolled
                            .stream()
                            .filter(ttiReportDto -> ttiReportDto.getTtiType().equals(ttiType))
                            .findAny().get());
                    ttiReportDataRowWrapper.setGraduates(graduates
                            .stream()
                            .filter(ttiReportDto -> ttiReportDto.getTtiType().equals(ttiType))
                            .findAny().get());
                    ttiReportDataRowWrapper.setAssessed(assessed
                            .stream()
                            .filter(ttiReportDto -> ttiReportDto.getTtiType().equals(ttiType))
                            .findAny().get());
                    ttiReportDataRowWrapper.setCertified(certified
                            .stream()
                            .filter(ttiReportDto -> ttiReportDto.getTtiType().equals(ttiType))
                            .findAny().get());
                    ttiReportDataRowWrapperList.add(ttiReportDataRowWrapper);
                }
        );
        return ttiReportDataRowWrapperList;
    }
}
