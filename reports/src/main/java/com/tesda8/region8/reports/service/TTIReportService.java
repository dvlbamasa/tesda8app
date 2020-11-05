package com.tesda8.region8.reports.service;

import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.reports.model.dto.TTIReportDto;

import java.util.List;

public interface TTIReportService {

    List<TTIReportDto> getAllTTIReport();

    List<TTIReportDto> getAllTTIReportByEgacType(EgacType egacType);

    List<TTIReportDto> updateTTIReports(List<TTIReportDto> ttiReportDtos);
}
