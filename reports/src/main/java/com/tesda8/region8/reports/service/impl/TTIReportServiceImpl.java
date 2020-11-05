package com.tesda8.region8.reports.service.impl;

import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.service.ReportUtil;
import com.tesda8.region8.reports.model.dto.TTIReportDto;
import com.tesda8.region8.reports.model.entities.DailyReportInfo;
import com.tesda8.region8.reports.model.entities.TTIReport;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import com.tesda8.region8.reports.repository.TTIReportRepository;
import com.tesda8.region8.reports.service.DailyReportInfoService;
import com.tesda8.region8.reports.service.TTIReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TTIReportServiceImpl implements TTIReportService {

    private TTIReportRepository ttiReportRepository;
    private ReportMapper reportMapper;
    private DailyReportInfoService dailyReportInfoService;


    public TTIReportServiceImpl(TTIReportRepository ttiReportRepository,
                                ReportMapper reportMapper, DailyReportInfoService dailyReportInfoService) {
        this.ttiReportRepository = ttiReportRepository;
        this.reportMapper = reportMapper;
        this.dailyReportInfoService = dailyReportInfoService;
    }

    @Override
    public List<TTIReportDto> getAllTTIReport() {
        List<TTIReport> ttiReports = ttiReportRepository.findAll();
        return ttiReports
                .stream()
                .map(ttiReport -> reportMapper.ttiReportToDto(ttiReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<TTIReportDto> getAllTTIReportByEgacType(EgacType egacType) {
        List<TTIReport> ttiReports = ttiReportRepository.findAllByEgacData_EgacTypeOrderById(egacType);
        return ttiReports
                .stream()
                .map(ttiReport -> reportMapper.ttiReportToDto(ttiReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<TTIReportDto> updateTTIReports(List<TTIReportDto> ttiReportDtos) {
        ttiReportDtos.forEach(
                ttiReportDto -> {
                    ttiReportRepository.findById(ttiReportDto.getId())
                            .ifPresent(ttiReport -> mapAndSaveTTIReport(ttiReport, ttiReportDto));
                }
        );
        DailyReportInfo dailyReportInfo = new DailyReportInfo();
        dailyReportInfo.setUpdatedDate(LocalDateTime.now());
        dailyReportInfo.setUpdatedBy("SYSTEM");
        dailyReportInfoService.saveDailyReportInfo(dailyReportInfo);
        return ttiReportDtos;
    }

    private void mapAndSaveTTIReport(TTIReport ttiReport, TTIReportDto ttiReportDto) {
        ttiReport.getEgacData().setOutput(ttiReportDto.getEgacDataDto().getOutput());
        ttiReport.getEgacData().setTarget(ttiReportDto.getEgacDataDto().getTarget());
        ttiReport.getEgacData().setRate(ReportUtil.calculateRate(ttiReportDto.getEgacDataDto().getTarget(),
                ttiReportDto.getEgacDataDto().getOutput()));
        ttiReport.setUpdatedDate(LocalDateTime.now());
        ttiReportRepository.save(ttiReport);
    }
}
