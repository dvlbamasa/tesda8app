package com.tesda8.region8.web.service.impl;

import com.tesda8.region8.util.service.ReportUtil;
import com.tesda8.region8.web.model.dto.GeneralReportDto;
import com.tesda8.region8.web.model.entities.DailyReportInfo;
import com.tesda8.region8.web.model.entities.EgacData;
import com.tesda8.region8.web.model.entities.GeneralReport;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.web.model.mapper.ReportMapper;
import com.tesda8.region8.web.repository.GeneralReportRepository;
import com.tesda8.region8.web.service.DailyReportInfoService;
import com.tesda8.region8.web.service.GeneralReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralReportServiceImpl implements GeneralReportService {

    private DailyReportInfoService dailyReportInfoService;
    private GeneralReportRepository generalReportRepository;
    private ReportMapper reportMapper;

    private static Logger logger = LoggerFactory.getLogger(GeneralReportServiceImpl.class);

    @Autowired
    public GeneralReportServiceImpl(GeneralReportRepository generalReportRepository,
                                    ReportMapper reportMapper,
                                    DailyReportInfoService dailyReportInfoService) {
        this.generalReportRepository = generalReportRepository;
        this.reportMapper = reportMapper;
        this.dailyReportInfoService = dailyReportInfoService;
    }

    @Override
    public List<GeneralReportDto> findAllGeneralReport() {
        List<GeneralReport> generalReports;
        generalReports = generalReportRepository.findAll();
        return generalReports.stream()
                .map(generalReport -> reportMapper.generalReportToDto(generalReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralReportDto> findAllGeneralReportByEgacTypeAndDailyReportType(EgacType egacType,
                                                                                   DailyReportType dailyReportType) {
        List<GeneralReport> generalReports;
        generalReports = generalReportRepository.findAllByEgacData_EgacTypeAndAndDailyReportTypeOrderById(egacType,
                                                                                                dailyReportType);
        return generalReports.stream()
                .map(generalReport -> reportMapper.generalReportToDto(generalReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralReportDto> findAllGeneralReportByDailyReportType(DailyReportType dailyReportType) {
        List<GeneralReport> generalReports;
        generalReports = generalReportRepository.findAllByDailyReportTypeOrderById(dailyReportType);
        return generalReports.stream()
                .map(generalReport -> reportMapper.generalReportToDto(generalReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralReportDto> findAllGeneralReportByDailyReportTypeAndReportSourceType(DailyReportType dailyReportType, ReportSourceType reportSourceType) {
        List<GeneralReport> generalReports;
        generalReports = generalReportRepository.findAllByDailyReportTypeAndReportSourceTypeOrderById(dailyReportType, reportSourceType);
        return generalReports.stream()
                .map(generalReport -> reportMapper.generalReportToDto(generalReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralReportDto> findAllGeneralReportByDailyReportTypeAndReportSourceTypeAndEgacType(DailyReportType dailyReportType, ReportSourceType reportSourceType, EgacType egacType) {
        List<GeneralReport> generalReports;
        generalReports = generalReportRepository.findAllByDailyReportTypeAndReportSourceTypeAndEgacData_EgacTypeOrderById(dailyReportType, reportSourceType, egacType);
        return generalReports.stream()
                .map(generalReport -> reportMapper.generalReportToDto(generalReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralReportDto> updateGeneralReports(List<GeneralReportDto> generalReports) {
        generalReports.forEach(
                generalReportDto -> {
                    generalReportRepository.findById(generalReportDto.getId())
                            .ifPresent(generalReport -> mapAndSaveGeneralReport(generalReportDto, generalReport));
                }
        );

        boolean checkIfPoReport =
                generalReports.stream()
                        .anyMatch(generalReportDto ->
                                generalReportDto.getDailyReportType().equals(DailyReportType.PO_REPORT));

        DailyReportInfo dailyReportInfo = new DailyReportInfo();
        dailyReportInfo.setUpdatedDate(LocalDateTime.now());
        dailyReportInfo.setUpdatedBy("SYSTEM");
        dailyReportInfoService.saveDailyReportInfo(dailyReportInfo);
        return generalReports;
    }

    private void mapAndSaveGeneralReport(GeneralReportDto generalReportDto, GeneralReport generalReport) {
        EgacData egacData = generalReport.getEgacData();
        egacData.setOutput(generalReportDto.getEgacDataDto().getOutput());
        egacData.setTarget(generalReportDto.getEgacDataDto().getTarget());
        egacData.setRate(ReportUtil.calculateRate(generalReportDto.getEgacDataDto().getTarget(),
                generalReportDto.getEgacDataDto().getOutput()));

        generalReport.setEgacData(egacData);
        generalReport.setUpdatedDate(LocalDateTime.now());
        generalReportRepository.save(generalReport);
    }
}
