package com.tesda8.region8.reports.service.impl;

import com.tesda8.region8.util.service.ReportUtil;
import com.tesda8.region8.reports.model.dto.ROPerModeReportDto;
import com.tesda8.region8.reports.model.entities.DailyReportInfo;
import com.tesda8.region8.reports.model.entities.EgacData;
import com.tesda8.region8.reports.model.entities.ROPerModeReport;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import com.tesda8.region8.reports.repository.ROPerModeReportRepository;
import com.tesda8.region8.reports.service.DailyReportInfoService;
import com.tesda8.region8.reports.service.ROPerModeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ROPerModeServiceImpl implements ROPerModeReportService {

    private ROPerModeReportRepository roPerModeReportRepository;
    private ReportMapper reportMapper;
    private DailyReportInfoService dailyReportInfoService;

    @Autowired
    public ROPerModeServiceImpl(ROPerModeReportRepository roPerModeReportRepository,
                                ReportMapper reportMapper,
                                DailyReportInfoService dailyReportInfoService) {
        this.roPerModeReportRepository = roPerModeReportRepository;
        this.reportMapper = reportMapper;
        this.dailyReportInfoService = dailyReportInfoService;
    }

    @Override
    public List<ROPerModeReportDto> findAllROPerModeReports() {
        List<ROPerModeReport> roPerModeReports = new ArrayList<>();
        roPerModeReports = roPerModeReportRepository.findAll();
        return roPerModeReports.stream()
                .map(roPerModeReport -> reportMapper.roPerModeReportToDto(roPerModeReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<ROPerModeReportDto> findAllROPerModeReportsByEgacTypeAndReportSourceType(EgacType egacType,
                                                                                         ReportSourceType reportSourceType) {
        List<ROPerModeReport> roPerModeReports = new ArrayList<>();
        roPerModeReports = roPerModeReportRepository.findAllByEgacData_EgacTypeAndReportSourceTypeOrderById(egacType,
                reportSourceType);
        return roPerModeReports.stream()
                .map(roPerModeReport -> reportMapper.roPerModeReportToDto(roPerModeReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<ROPerModeReportDto> findAllROPerModeReportsByReportSourceType(ReportSourceType reportSourceType) {
        List<ROPerModeReport> roPerModeReports;
        roPerModeReports = roPerModeReportRepository.findAllByReportSourceTypeOrderById(reportSourceType);
        return roPerModeReports.stream()
                .map(roPerModeReport -> reportMapper.roPerModeReportToDto(roPerModeReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<ROPerModeReportDto> updateROPerModeReports(List<ROPerModeReportDto> roPerModeReportDtos) {
        roPerModeReportDtos.forEach(
                roPerModeReportDto -> {
                    roPerModeReportRepository.findById(roPerModeReportDto.getId())
                            .ifPresent(roPerModeReport -> mapAndSaveROPerModeReport(roPerModeReport, roPerModeReportDto));
                }
        );
        DailyReportInfo dailyReportInfo = new DailyReportInfo();
        dailyReportInfo.setUpdatedDate(LocalDateTime.now());
        dailyReportInfo.setUpdatedBy("SYSTEM");
        dailyReportInfoService.saveDailyReportInfo(dailyReportInfo);
        return roPerModeReportDtos;
    }

    private void mapAndSaveROPerModeReport(ROPerModeReport roPerModeReport, ROPerModeReportDto roPerModeReportDto) {
        reportMapper.updatedROPerMode(roPerModeReportDto, roPerModeReport);
        roPerModeReport.getEgacData().setRate(ReportUtil.calculateRate(roPerModeReportDto.getEgacDataDto().getTarget(),
                roPerModeReportDto.getEgacDataDto().getOutput()));
        roPerModeReportRepository.save(roPerModeReport);
    }


}
