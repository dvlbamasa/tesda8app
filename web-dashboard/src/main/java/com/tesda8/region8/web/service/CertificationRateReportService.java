package com.tesda8.region8.web.service;

import com.tesda8.region8.web.model.dto.CertificationRateReportDto;

import java.util.List;

public interface CertificationRateReportService {

    List<CertificationRateReportDto> findAllCertificationRate();

    List<CertificationRateReportDto> updateCertificationRateReports(List<CertificationRateReportDto> certificationRateReportDtos);
}
