package com.tesda8.region8.web.model.dto.wrapper;

import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.reports.model.dto.TTIReportDto;
import lombok.Data;

import java.util.List;

@Data
public class GeneralReportsDtoWrapper {

    private List<GeneralReportDto> poReports;
    private List<GeneralReportDto> institutionBasedReports;
    private List<GeneralReportDto> enterpriseBasedGSReports;
    private List<GeneralReportDto> enterpriseBasedT2Reports;
    private List<GeneralReportDto> communityBasedReports;
    private List<TTIReportDto> ttiReports;
    private Month month;
    private int year;
}
