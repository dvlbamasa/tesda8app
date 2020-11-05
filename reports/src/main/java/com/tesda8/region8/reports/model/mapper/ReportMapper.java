package com.tesda8.region8.reports.model.mapper;

import com.tesda8.region8.reports.model.dto.TTIReportDto;
import com.tesda8.region8.reports.model.entities.CertificationRateReport;
import com.tesda8.region8.reports.model.entities.EgacData;
import com.tesda8.region8.reports.model.entities.GeneralReport;
import com.tesda8.region8.reports.model.entities.MonthlyReport;
import com.tesda8.region8.reports.model.entities.OperatingUnit;
import com.tesda8.region8.reports.model.entities.ROPerModeReport;
import com.tesda8.region8.reports.model.dto.CertificationRateReportDto;
import com.tesda8.region8.reports.model.dto.EgacDataDto;
import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.reports.model.dto.MonthlyReportDto;
import com.tesda8.region8.reports.model.dto.OperatingUnitDto;
import com.tesda8.region8.reports.model.dto.ROPerModeReportDto;
import com.tesda8.region8.reports.model.entities.TTIReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    EgacDataDto egacDataToDto(EgacData egacData);
    EgacData egacDataToEntity(EgacDataDto egacDataDto);
    EgacDataDto egacDataDtoToDto(EgacDataDto egacDataDto);

    CertificationRateReportDto certificationReportToDto(CertificationRateReport certificationRateReport);
    CertificationRateReport certificationReportToEntity(CertificationRateReportDto certificationRateReportDto);

    @Mapping(source = "generalReport.egacData", target = "egacDataDto")
    GeneralReportDto generalReportToDto(GeneralReport generalReport);
    GeneralReport generalReportToEntity(GeneralReportDto generalReportDto);

    @Mapping(source = "roPerModeReport.egacData", target = "egacDataDto")
    ROPerModeReportDto roPerModeReportToDto(ROPerModeReport roPerModeReport);
    ROPerModeReport roPerModeReportToEntity(ROPerModeReportDto roPerModeReportDto);

    @Mapping(source = "ttiReport.egacData", target = "egacDataDto")
    TTIReportDto ttiReportToDto(TTIReport ttiReport);
    TTIReport ttiReportToEntity(TTIReportDto ttiReportDto);

    OperatingUnitDto operatingUnitToDto(OperatingUnit operatingUnit);
    OperatingUnit operatingUnitToEntity(OperatingUnitDto operatingUnitDto);

    @Mapping(source = "monthlyReport.egacData", target = "egacDataDto")
    MonthlyReportDto monthlyReportToDto(MonthlyReport monthlyReport);

    @Mapping(source = "monthlyReportDto.egacDataDto", target = "egacData")
    MonthlyReport monthlyReportToEntity(MonthlyReportDto monthlyReportDto);

    @Mapping(source = "generalReportDto.egacDataDto", target = "egacDataDto")
    MonthlyReportDto generalReportToMonthlyDto(GeneralReportDto generalReportDto);

}
