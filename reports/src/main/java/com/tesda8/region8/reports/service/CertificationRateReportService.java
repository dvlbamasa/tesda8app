package com.tesda8.region8.reports.service;

import com.tesda8.region8.reports.model.dto.CertificationRateReportDto;

import java.util.List;

public interface CertificationRateReportService {

    List<CertificationRateReportDto> findAllCertificationRate();

    List<CertificationRateReportDto> updateCertificationRateReports(List<CertificationRateReportDto> certificationRateReportDtos);
}
