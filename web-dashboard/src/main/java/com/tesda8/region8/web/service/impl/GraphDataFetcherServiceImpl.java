package com.tesda8.region8.web.service.impl;

import com.google.common.collect.Lists;
import com.tesda8.region8.util.model.DataPoints;
import com.tesda8.region8.util.service.ReportUtil;
import com.tesda8.region8.reports.model.dto.TTIReportDto;
import com.tesda8.region8.web.model.dto.graph.GraphDataList;
import com.tesda8.region8.reports.model.dto.CertificationRateReportDto;
import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.reports.model.dto.MonthlyReportDto;
import com.tesda8.region8.reports.model.dto.ROPerModeReportDto;
import com.tesda8.region8.web.model.dto.RateData;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.DataPointType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.reports.service.CertificationRateReportService;
import com.tesda8.region8.reports.service.GeneralReportService;
import com.tesda8.region8.web.service.GraphDataFetcherService;
import com.tesda8.region8.reports.service.OperatingUnitService;
import com.tesda8.region8.reports.service.ROPerModeReportService;
import com.tesda8.region8.reports.service.TTIReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GraphDataFetcherServiceImpl implements GraphDataFetcherService {

    private static Logger logger = LoggerFactory.getLogger(GraphDataFetcherServiceImpl.class);

    private CertificationRateReportService certificationRateReportService;
    private ROPerModeReportService roPerModeReportService;
    private GeneralReportService generalReportService;
    private OperatingUnitService operatingUnitService;
    private TTIReportService ttiReportService;

    @Autowired
    public GraphDataFetcherServiceImpl(CertificationRateReportService certificationRateReportService,
                                       ROPerModeReportService roPerModeReportService,
                                       GeneralReportService generalReportService,
                                       OperatingUnitService operatingUnitService,
                                       TTIReportService ttiReportService) {
        this.certificationRateReportService = certificationRateReportService;
        this.roPerModeReportService = roPerModeReportService;
        this.generalReportService = generalReportService;
        this.operatingUnitService = operatingUnitService;
        this.ttiReportService = ttiReportService;
    }

    @Override
    public List<DataPoints> fetchCertificationRateData(DataPointType dataPointType) {
        List<DataPoints> dataPoints = Lists.newArrayList();
        List<CertificationRateReportDto> certificationRateReportDtos =
                certificationRateReportService.findAllCertificationRate();
        DataPoints total = ReportUtil.initializeTotalDataPoints();

        certificationRateReportDtos.forEach(
                certificationRateReportDto -> {
                    DataPoints newDataPoints = new DataPoints();
                    newDataPoints.setLabel(certificationRateReportDto.getOperatingUnitType().label);
                    switch (dataPointType) {
                        case ASSESSED:
                            newDataPoints.setValue(certificationRateReportDto.getAssessed());
                            total.setValue(certificationRateReportDto.getAssessed()+total.getValue());
                            break;
                        case CERTIFIED:
                            newDataPoints.setValue(certificationRateReportDto.getCertified());
                            total.setValue(certificationRateReportDto.getCertified()+total.getValue());
                            break;
                        case RATE:
                            newDataPoints.setValue(Double.valueOf(certificationRateReportDto.getRate()).longValue());
                            total.setValue(Double.valueOf(certificationRateReportDto.getRate()).longValue()+total.getValue());
                            break;
                        default:
                            break;
                    }
                    dataPoints.add(newDataPoints);
                }
        );
        if (dataPointType.equals(DataPointType.RATE)) {
            total.setValue(Math.round(total.getValue()/6));
        }
        dataPoints.add(total);
        return dataPoints;
    }

    @Override
    public List<DataPoints> fetchTTIReportsData(DataPointType dataPointType, EgacType egacType) {
        List<DataPoints> dataPoints = Lists.newArrayList();
        List<TTIReportDto> ttiReports = ttiReportService.getAllTTIReportByEgacType(egacType);
        DataPoints total = ReportUtil.initializeTotalDataPoints();
        RateData rateData = new RateData();
        ttiReports.forEach(
                ttiReportDto -> {
                    if (ttiReportDto.getEgacDataDto().getTarget() != 0) {
                        DataPoints newDataPoints = new DataPoints();
                        newDataPoints.setLabel(ttiReportDto.getTtiType().label);
                        switch (dataPointType) {
                            case TARGET:
                                newDataPoints.setValue(ttiReportDto.getEgacDataDto().getTarget());
                                total.setValue(total.getValue()+ttiReportDto.getEgacDataDto().getTarget());
                                break;
                            case OUTPUT:
                                newDataPoints.setValue(ttiReportDto.getEgacDataDto().getOutput());
                                total.setValue(total.getValue()+ttiReportDto.getEgacDataDto().getOutput());
                                break;
                            case RATE:
                                newDataPoints.setValue(Double.valueOf(ttiReportDto.getEgacDataDto().getRate()).longValue());
                                rateData.setOutputs(rateData.getOutputs()+ttiReportDto.getEgacDataDto().getOutput());
                                rateData.setTargets(rateData.getTargets()+ttiReportDto.getEgacDataDto().getTarget());
                                break;
                            default:
                                break;
                        }
                        dataPoints.add(newDataPoints);
                    }
                }
        );
        if (dataPointType.equals(DataPointType.RATE)) {
            total.setValue(Double.valueOf(ReportUtil.calculateRate(rateData.getTargets(), rateData.getOutputs())).longValue());
        }
        dataPoints.add(total);
        return dataPoints;
    }

    @Override
    public List<DataPoints> fetchROPerModeReportsData(DataPointType dataPointType, EgacType egacType, ReportSourceType reportSourceType) {
        List<DataPoints> dataPoints = Lists.newArrayList();
        List<ROPerModeReportDto> roPerModeReportDtos =
                roPerModeReportService.findAllROPerModeReportsByEgacTypeAndReportSourceType(egacType, reportSourceType);
        DataPoints total = ReportUtil.initializeTotalDataPoints();
        RateData rateData = new RateData();
        roPerModeReportDtos.forEach(
                roPerModeReportDto -> {
                    DataPoints newDataPoints = new DataPoints();
                    newDataPoints.setLabel(roPerModeReportDto.getDeliveryMode().label);
                    switch (dataPointType) {
                        case OUTPUT:
                            newDataPoints.setValue(roPerModeReportDto.getEgacDataDto().getOutput());
                            total.setValue(roPerModeReportDto.getEgacDataDto().getOutput() + total.getValue());
                            break;
                        case TARGET:
                            newDataPoints.setValue(roPerModeReportDto.getEgacDataDto().getTarget());
                            total.setValue(roPerModeReportDto.getEgacDataDto().getTarget() + total.getValue());
                            break;
                        case RATE:
                            newDataPoints.setValue(Double.valueOf(roPerModeReportDto.getEgacDataDto().getRate()).longValue());
                            rateData.setOutputs(rateData.getOutputs() + roPerModeReportDto.getEgacDataDto().getOutput());
                            rateData.setTargets(rateData.getTargets() + roPerModeReportDto.getEgacDataDto().getTarget());
                            break;
                        default:
                            break;
                    }
                    dataPoints.add(newDataPoints);
                }
        );
        if (dataPointType.equals(DataPointType.RATE)) {
            total.setValue(Double.valueOf(ReportUtil.calculateRate(rateData.getTargets(), rateData.getOutputs())).longValue());
        }
        dataPoints.add(total);
        return dataPoints;
    }

    @Override
    public List<DataPoints> fetchGeneralReportsData(DataPointType dataPointType, EgacType egacType, ReportSourceType reportSourceType, DailyReportType dailyReportType) {
        List<DataPoints> dataPoints = Lists.newArrayList();
        List<GeneralReportDto> generalReportDtos =
                generalReportService.findAllGeneralReportByDailyReportTypeAndReportSourceTypeAndEgacType(dailyReportType,reportSourceType, egacType);
        DataPoints total = ReportUtil.initializeTotalDataPoints();
        RateData rateData = new RateData();
        generalReportDtos.forEach(
                generalReportDto -> {
                    DataPoints newDataPoints = new DataPoints();
                    newDataPoints.setLabel(generalReportDto.getOperatingUnitType().label);
                    switch (dataPointType){
                        case OUTPUT:
                            newDataPoints.setValue(generalReportDto.getEgacDataDto().getOutput());
                            total.setValue(generalReportDto.getEgacDataDto().getOutput() + total.getValue());
                            break;
                        case TARGET:
                            newDataPoints.setValue(generalReportDto.getEgacDataDto().getTarget());
                            total.setValue(generalReportDto.getEgacDataDto().getTarget() + total.getValue());
                            break;
                        case RATE:
                            newDataPoints.setValue(Double.valueOf(generalReportDto.getEgacDataDto().getRate()).longValue());
                            rateData.setOutputs(rateData.getOutputs() + generalReportDto.getEgacDataDto().getOutput());
                            rateData.setTargets(rateData.getTargets() + generalReportDto.getEgacDataDto().getTarget());
                            break;
                        default:
                            break;
                    }
                    dataPoints.add(newDataPoints);
                }
        );
        if (dataPointType.equals(DataPointType.RATE)) {
            total.setValue(Double.valueOf(ReportUtil.calculateRate(rateData.getTargets(), rateData.getOutputs())).longValue());
        }
        dataPoints.add(total);
        return dataPoints;
    }

    @Override
    public List<DataPoints> fetchMonthlyReportsData(DataPointType dataPointType, OperatingUnitType operatingUnitType, EgacType egacType, int year) {
        List<DataPoints> dataPoints = Lists.newArrayList();
        List<MonthlyReportDto> monthlyReportsResult;
        List<MonthlyReportDto> monthlyReports = operatingUnitService.getOperatingUnit(operatingUnitType).getMonthlyReports();
        monthlyReportsResult = monthlyReports.stream()
                .filter(monthlyReportDto -> monthlyReportDto.getEgacDataDto().getEgacType().equals(egacType))
                .filter(monthlyReportDto -> monthlyReportDto.getYear() == year)
                .collect(Collectors.toList());
        List<MonthlyReportDto> sortedMonthlyReports = Lists.newArrayList();
        Arrays.asList(Month.values()).forEach(
                month -> {
                    monthlyReportsResult.forEach(monthlyReportDto -> {
                        if (monthlyReportDto.getMonth().equals(month)) {
                            sortedMonthlyReports.add(monthlyReportDto);
                        }
                    });
                }
        );
        sortedMonthlyReports.forEach(
                monthlyReportDto -> {
                    DataPoints newDataPoints = new DataPoints();
                    newDataPoints.setLabel(monthlyReportDto.getMonth().label);
                    switch (dataPointType) {
                        case OUTPUT:
                            newDataPoints.setValue(monthlyReportDto.getEgacDataDto().getOutput());
                            break;
                        case TARGET:
                            newDataPoints.setValue(monthlyReportDto.getEgacDataDto().getTarget());
                            break;
                        case RATE:
                            newDataPoints.setValue(Double.valueOf(monthlyReportDto.getEgacDataDto().getRate()).longValue());
                            break;
                        default:
                            break;
                    }
                    dataPoints.add(newDataPoints);
                }
        );
        return dataPoints;
    }

    @Override
    public GraphDataList fetchGeneralDataList(EgacType egacType, ReportSourceType reportSourceType, DailyReportType dailyReportType) {
        GraphDataList graphDataList = new GraphDataList().build();
        graphDataList.getTargetData().setDataPoints(fetchGeneralReportsData(DataPointType.TARGET, egacType,reportSourceType,dailyReportType));
        graphDataList.getOutputData().setDataPoints(fetchGeneralReportsData(DataPointType.OUTPUT, egacType,reportSourceType,dailyReportType));
        graphDataList.getRateData().setDataPoints(fetchGeneralReportsData(DataPointType.RATE, egacType,reportSourceType,dailyReportType));
        return graphDataList;
    }

    @Override
    public GraphDataList fetchCertificationRateDataList() {
        GraphDataList graphDataList = new GraphDataList().build();
        graphDataList.getTargetData().setDataPoints(fetchCertificationRateData(DataPointType.ASSESSED));
        graphDataList.getOutputData().setDataPoints(fetchCertificationRateData(DataPointType.CERTIFIED));
        graphDataList.getRateData().setDataPoints(fetchCertificationRateData(DataPointType.RATE));
        return graphDataList;
    }

    @Override
    public GraphDataList fetchTTIReportDataList(EgacType egacType) {
        GraphDataList graphDataList = new GraphDataList().build();
        graphDataList.getTargetData().setDataPoints(fetchTTIReportsData(DataPointType.TARGET, egacType));
        graphDataList.getOutputData().setDataPoints(fetchTTIReportsData(DataPointType.OUTPUT, egacType));
        graphDataList.getRateData().setDataPoints(fetchTTIReportsData(DataPointType.RATE, egacType));
        return graphDataList;
    }


    @Override
    public GraphDataList fetchROPerModeReportsDataList(EgacType egacType, ReportSourceType reportSourceType) {
        GraphDataList graphDataList = new GraphDataList().build();
        graphDataList.getTargetData().setDataPoints(fetchROPerModeReportsData(DataPointType.TARGET, egacType, reportSourceType));
        graphDataList.getOutputData().setDataPoints(fetchROPerModeReportsData(DataPointType.OUTPUT, egacType, reportSourceType));
        graphDataList.getRateData().setDataPoints(fetchROPerModeReportsData(DataPointType.RATE, egacType, reportSourceType));
        return graphDataList;
    }

    @Override
    public GraphDataList fetchMonthlyReportDataList(EgacType egacType, OperatingUnitType operatingUnitType, int year) {
        GraphDataList graphDataList = new GraphDataList().build();
        graphDataList.getTargetData().setDataPoints(fetchMonthlyReportsData(DataPointType.TARGET, operatingUnitType, egacType, year));
        graphDataList.getOutputData().setDataPoints(fetchMonthlyReportsData(DataPointType.OUTPUT, operatingUnitType, egacType, year));
        graphDataList.getRateData().setDataPoints(fetchMonthlyReportsData(DataPointType.RATE, operatingUnitType, egacType, year));
        return graphDataList;
    }


}
