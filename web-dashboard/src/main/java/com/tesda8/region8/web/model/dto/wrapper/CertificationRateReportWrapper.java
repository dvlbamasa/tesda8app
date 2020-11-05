package com.tesda8.region8.web.model.dto.wrapper;

import com.tesda8.region8.reports.model.dto.CertificationRateReportDto;
import lombok.Data;

import java.util.List;

@Data
public class CertificationRateReportWrapper {

    private List<CertificationRateReportDto> certificationRateReportDtos;
}
