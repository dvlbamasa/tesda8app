package com.tesda8.region8.web.model.dto.wrapper;

import com.tesda8.region8.reports.model.dto.CertificationRateReportDto;
import com.tesda8.region8.reports.model.wrapper.GeneralReportDataRowEGWrapper;
import com.tesda8.region8.reports.model.wrapper.GeneralReportDataRowWrapper;
import com.tesda8.region8.reports.model.wrapper.RoPerModeDataRowWrapper;
import com.tesda8.region8.reports.model.wrapper.TTIReportDataRowWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GeneralReportTableWrapper {

    private List<GeneralReportDataRowWrapper> poReports;
    private List<GeneralReportDataRowEGWrapper> institutionBasedReports;
    private List<GeneralReportDataRowEGWrapper> communityBasedReports;
    private List<GeneralReportDataRowEGWrapper> enterpriseBasedGSReports;
    private List<GeneralReportDataRowEGWrapper> enterpriseBasedT2Reports;
    private List<RoPerModeDataRowWrapper> roPerModeGSReports;
    private List<RoPerModeDataRowWrapper> roPerModeT2Reports;
    private List<TTIReportDataRowWrapper> ttiReports;
    private List<CertificationRateReportDto> certificationRateReports;
}
