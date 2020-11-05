package com.tesda8.region8.reports.service.impl;

import com.tesda8.region8.util.service.ReportUtil;
import com.tesda8.region8.reports.model.dto.CertificationRateReportDto;
import com.tesda8.region8.reports.model.entities.CertificationRateReport;
import com.tesda8.region8.reports.model.entities.DailyReportInfo;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import com.tesda8.region8.reports.repository.CertificationRateReportRepository;
import com.tesda8.region8.reports.service.CertificationRateReportService;
import com.tesda8.region8.reports.service.DailyReportInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificationRateReportServiceImpl implements CertificationRateReportService {

    private DailyReportInfoService dailyReportInfoService;
    private CertificationRateReportRepository certificationRateReportRepository;
    private ReportMapper reportMapper;

    @Autowired
    public CertificationRateReportServiceImpl(DailyReportInfoService dailyReportInfoService,
                                            CertificationRateReportRepository certificationRateReportRepository,
                                              ReportMapper reportMapper) {
        this.dailyReportInfoService = dailyReportInfoService;
        this.certificationRateReportRepository = certificationRateReportRepository;
        this.reportMapper = reportMapper;
    }

    @Override
    public List<CertificationRateReportDto> findAllCertificationRate() {
        List<CertificationRateReport> certificationRateReports;
        certificationRateReports = certificationRateReportRepository.findAll();
        return certificationRateReports.stream()
                .map(certificationRateReport -> reportMapper.certificationReportToDto(certificationRateReport))
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificationRateReportDto> updateCertificationRateReports(List<CertificationRateReportDto>
                                                                                       certificationRateReportDtos) {
        certificationRateReportDtos.forEach(
                certificationRateReportDto -> {
                    certificationRateReportRepository.findById(certificationRateReportDto.getId())
                            .ifPresent(certificationRateReport -> mapAndSaveCertificationRateReport(
                                    certificationRateReport, certificationRateReportDto));
                }
        );
        DailyReportInfo dailyReportInfo = new DailyReportInfo();
        dailyReportInfo.setUpdatedDate(LocalDateTime.now());
        dailyReportInfo.setUpdatedBy("SYSTEM");
        dailyReportInfoService.saveDailyReportInfo(dailyReportInfo);
        return certificationRateReportDtos;
    }

    private void mapAndSaveCertificationRateReport(CertificationRateReport certificationRateReport,
                                                   CertificationRateReportDto certificationRateReportDto) {
        certificationRateReport.setAssessed(certificationRateReportDto.getAssessed());
        certificationRateReport.setCertified(certificationRateReportDto.getCertified());
        certificationRateReport.setRate(ReportUtil.calculateRate(certificationRateReportDto.getAssessed(),
                                certificationRateReportDto.getCertified()));
        certificationRateReport.setUpdatedDate(LocalDateTime.now());
        certificationRateReportRepository.save(certificationRateReport);
    }
}
