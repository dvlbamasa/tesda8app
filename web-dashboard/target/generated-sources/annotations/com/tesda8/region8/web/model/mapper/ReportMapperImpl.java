package com.tesda8.region8.web.model.mapper;

import com.tesda8.region8.web.model.dto.CertificationRateReportDto;
import com.tesda8.region8.web.model.dto.EgacDataDto;
import com.tesda8.region8.web.model.dto.GeneralReportDto;
import com.tesda8.region8.web.model.dto.MonthlyReportDto;
import com.tesda8.region8.web.model.dto.OperatingUnitDto;
import com.tesda8.region8.web.model.dto.ROPerModeReportDto;
import com.tesda8.region8.web.model.entities.CertificationRateReport;
import com.tesda8.region8.web.model.entities.EgacData;
import com.tesda8.region8.web.model.entities.GeneralReport;
import com.tesda8.region8.web.model.entities.MonthlyReport;
import com.tesda8.region8.web.model.entities.OperatingUnit;
import com.tesda8.region8.web.model.entities.ROPerModeReport;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-26T09:47:11+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public EgacDataDto egacDataToDto(EgacData egacData) {
        if ( egacData == null ) {
            return null;
        }

        EgacDataDto egacDataDto = new EgacDataDto();

        egacDataDto.setEgacType( egacData.getEgacType() );
        egacDataDto.setTarget( egacData.getTarget() );
        egacDataDto.setOutput( egacData.getOutput() );
        egacDataDto.setRate( egacData.getRate() );

        return egacDataDto;
    }

    @Override
    public EgacData egacDataToEntity(EgacDataDto egacDataDto) {
        if ( egacDataDto == null ) {
            return null;
        }

        EgacData egacData = new EgacData();

        egacData.setEgacType( egacDataDto.getEgacType() );
        egacData.setTarget( egacDataDto.getTarget() );
        egacData.setOutput( egacDataDto.getOutput() );
        egacData.setRate( egacDataDto.getRate() );

        return egacData;
    }

    @Override
    public EgacDataDto egacDataDtoToDto(EgacDataDto egacDataDto) {
        if ( egacDataDto == null ) {
            return null;
        }

        EgacDataDto egacDataDto1 = new EgacDataDto();

        egacDataDto1.setEgacType( egacDataDto.getEgacType() );
        egacDataDto1.setTarget( egacDataDto.getTarget() );
        egacDataDto1.setOutput( egacDataDto.getOutput() );
        egacDataDto1.setRate( egacDataDto.getRate() );

        return egacDataDto1;
    }

    @Override
    public CertificationRateReportDto certificationReportToDto(CertificationRateReport certificationRateReport) {
        if ( certificationRateReport == null ) {
            return null;
        }

        CertificationRateReportDto certificationRateReportDto = new CertificationRateReportDto();

        if ( certificationRateReport.getId() != null ) {
            certificationRateReportDto.setId( certificationRateReport.getId() );
        }
        if ( certificationRateReport.getCreatedDate() != null ) {
            certificationRateReportDto.setCreatedDate( Date.from( certificationRateReport.getCreatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        if ( certificationRateReport.getUpdatedDate() != null ) {
            certificationRateReportDto.setUpdatedDate( Date.from( certificationRateReport.getUpdatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        certificationRateReportDto.setCreatedBy( certificationRateReport.getCreatedBy() );
        certificationRateReportDto.setUpdatedBy( certificationRateReport.getUpdatedBy() );
        certificationRateReportDto.setOperatingUnitType( certificationRateReport.getOperatingUnitType() );
        certificationRateReportDto.setAssessed( certificationRateReport.getAssessed() );
        certificationRateReportDto.setCertified( certificationRateReport.getCertified() );
        certificationRateReportDto.setRate( certificationRateReport.getRate() );

        return certificationRateReportDto;
    }

    @Override
    public CertificationRateReport certificationReportToEntity(CertificationRateReportDto certificationRateReportDto) {
        if ( certificationRateReportDto == null ) {
            return null;
        }

        CertificationRateReport certificationRateReport = new CertificationRateReport();

        certificationRateReport.setId( certificationRateReportDto.getId() );
        if ( certificationRateReportDto.getCreatedDate() != null ) {
            certificationRateReport.setCreatedDate( LocalDateTime.ofInstant( certificationRateReportDto.getCreatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( certificationRateReportDto.getUpdatedDate() != null ) {
            certificationRateReport.setUpdatedDate( LocalDateTime.ofInstant( certificationRateReportDto.getUpdatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        certificationRateReport.setCreatedBy( certificationRateReportDto.getCreatedBy() );
        certificationRateReport.setUpdatedBy( certificationRateReportDto.getUpdatedBy() );
        certificationRateReport.setOperatingUnitType( certificationRateReportDto.getOperatingUnitType() );
        certificationRateReport.setAssessed( certificationRateReportDto.getAssessed() );
        certificationRateReport.setCertified( certificationRateReportDto.getCertified() );
        certificationRateReport.setRate( certificationRateReportDto.getRate() );

        return certificationRateReport;
    }

    @Override
    public GeneralReportDto generalReportToDto(GeneralReport generalReport) {
        if ( generalReport == null ) {
            return null;
        }

        GeneralReportDto generalReportDto = new GeneralReportDto();

        generalReportDto.setEgacDataDto( egacDataToDto( generalReport.getEgacData() ) );
        if ( generalReport.getId() != null ) {
            generalReportDto.setId( generalReport.getId() );
        }
        if ( generalReport.getCreatedDate() != null ) {
            generalReportDto.setCreatedDate( Date.from( generalReport.getCreatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        if ( generalReport.getUpdatedDate() != null ) {
            generalReportDto.setUpdatedDate( Date.from( generalReport.getUpdatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        generalReportDto.setCreatedBy( generalReport.getCreatedBy() );
        generalReportDto.setUpdatedBy( generalReport.getUpdatedBy() );
        generalReportDto.setOperatingUnitType( generalReport.getOperatingUnitType() );
        generalReportDto.setReportSourceType( generalReport.getReportSourceType() );
        generalReportDto.setDailyReportType( generalReport.getDailyReportType() );

        return generalReportDto;
    }

    @Override
    public GeneralReport generalReportToEntity(GeneralReportDto generalReportDto) {
        if ( generalReportDto == null ) {
            return null;
        }

        GeneralReport generalReport = new GeneralReport();

        generalReport.setId( generalReportDto.getId() );
        if ( generalReportDto.getCreatedDate() != null ) {
            generalReport.setCreatedDate( LocalDateTime.ofInstant( generalReportDto.getCreatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( generalReportDto.getUpdatedDate() != null ) {
            generalReport.setUpdatedDate( LocalDateTime.ofInstant( generalReportDto.getUpdatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        generalReport.setCreatedBy( generalReportDto.getCreatedBy() );
        generalReport.setUpdatedBy( generalReportDto.getUpdatedBy() );
        generalReport.setOperatingUnitType( generalReportDto.getOperatingUnitType() );
        generalReport.setReportSourceType( generalReportDto.getReportSourceType() );
        generalReport.setDailyReportType( generalReportDto.getDailyReportType() );

        return generalReport;
    }

    @Override
    public ROPerModeReportDto roPerModeReportToDto(ROPerModeReport roPerModeReport) {
        if ( roPerModeReport == null ) {
            return null;
        }

        ROPerModeReportDto rOPerModeReportDto = new ROPerModeReportDto();

        rOPerModeReportDto.setEgacDataDto( egacDataToDto( roPerModeReport.getEgacData() ) );
        if ( roPerModeReport.getId() != null ) {
            rOPerModeReportDto.setId( roPerModeReport.getId() );
        }
        if ( roPerModeReport.getCreatedDate() != null ) {
            rOPerModeReportDto.setCreatedDate( Date.from( roPerModeReport.getCreatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        if ( roPerModeReport.getUpdatedDate() != null ) {
            rOPerModeReportDto.setUpdatedDate( Date.from( roPerModeReport.getUpdatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        rOPerModeReportDto.setCreatedBy( roPerModeReport.getCreatedBy() );
        rOPerModeReportDto.setUpdatedBy( roPerModeReport.getUpdatedBy() );
        rOPerModeReportDto.setDeliveryMode( roPerModeReport.getDeliveryMode() );
        rOPerModeReportDto.setReportSourceType( roPerModeReport.getReportSourceType() );

        return rOPerModeReportDto;
    }

    @Override
    public ROPerModeReport roPerModeReportToEntity(ROPerModeReportDto roPerModeReportDto) {
        if ( roPerModeReportDto == null ) {
            return null;
        }

        ROPerModeReport rOPerModeReport = new ROPerModeReport();

        rOPerModeReport.setId( roPerModeReportDto.getId() );
        if ( roPerModeReportDto.getCreatedDate() != null ) {
            rOPerModeReport.setCreatedDate( LocalDateTime.ofInstant( roPerModeReportDto.getCreatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( roPerModeReportDto.getUpdatedDate() != null ) {
            rOPerModeReport.setUpdatedDate( LocalDateTime.ofInstant( roPerModeReportDto.getUpdatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        rOPerModeReport.setCreatedBy( roPerModeReportDto.getCreatedBy() );
        rOPerModeReport.setUpdatedBy( roPerModeReportDto.getUpdatedBy() );
        rOPerModeReport.setDeliveryMode( roPerModeReportDto.getDeliveryMode() );
        rOPerModeReport.setReportSourceType( roPerModeReportDto.getReportSourceType() );

        return rOPerModeReport;
    }

    @Override
    public OperatingUnitDto operatingUnitToDto(OperatingUnit operatingUnit) {
        if ( operatingUnit == null ) {
            return null;
        }

        OperatingUnitDto operatingUnitDto = new OperatingUnitDto();

        if ( operatingUnit.getId() != null ) {
            operatingUnitDto.setId( operatingUnit.getId() );
        }
        if ( operatingUnit.getCreatedDate() != null ) {
            operatingUnitDto.setCreatedDate( Date.from( operatingUnit.getCreatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        if ( operatingUnit.getUpdatedDate() != null ) {
            operatingUnitDto.setUpdatedDate( Date.from( operatingUnit.getUpdatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        operatingUnitDto.setCreatedBy( operatingUnit.getCreatedBy() );
        operatingUnitDto.setUpdatedBy( operatingUnit.getUpdatedBy() );
        operatingUnitDto.setOperatingUnitType( operatingUnit.getOperatingUnitType() );
        operatingUnitDto.setMonthlyReports( monthlyReportListToMonthlyReportDtoList( operatingUnit.getMonthlyReports() ) );

        return operatingUnitDto;
    }

    @Override
    public OperatingUnit operatingUnitToEntity(OperatingUnitDto operatingUnitDto) {
        if ( operatingUnitDto == null ) {
            return null;
        }

        OperatingUnit operatingUnit = new OperatingUnit();

        operatingUnit.setId( operatingUnitDto.getId() );
        if ( operatingUnitDto.getCreatedDate() != null ) {
            operatingUnit.setCreatedDate( LocalDateTime.ofInstant( operatingUnitDto.getCreatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( operatingUnitDto.getUpdatedDate() != null ) {
            operatingUnit.setUpdatedDate( LocalDateTime.ofInstant( operatingUnitDto.getUpdatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        operatingUnit.setCreatedBy( operatingUnitDto.getCreatedBy() );
        operatingUnit.setUpdatedBy( operatingUnitDto.getUpdatedBy() );
        operatingUnit.setOperatingUnitType( operatingUnitDto.getOperatingUnitType() );
        operatingUnit.setMonthlyReports( monthlyReportDtoListToMonthlyReportList( operatingUnitDto.getMonthlyReports() ) );

        return operatingUnit;
    }

    @Override
    public MonthlyReportDto monthlyReportToDto(MonthlyReport monthlyReport) {
        if ( monthlyReport == null ) {
            return null;
        }

        MonthlyReportDto monthlyReportDto = new MonthlyReportDto();

        monthlyReportDto.setEgacDataDto( egacDataToDto( monthlyReport.getEgacData() ) );
        if ( monthlyReport.getId() != null ) {
            monthlyReportDto.setId( monthlyReport.getId() );
        }
        if ( monthlyReport.getCreatedDate() != null ) {
            monthlyReportDto.setCreatedDate( Date.from( monthlyReport.getCreatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        if ( monthlyReport.getUpdatedDate() != null ) {
            monthlyReportDto.setUpdatedDate( Date.from( monthlyReport.getUpdatedDate().toInstant( ZoneOffset.UTC ) ) );
        }
        monthlyReportDto.setCreatedBy( monthlyReport.getCreatedBy() );
        monthlyReportDto.setUpdatedBy( monthlyReport.getUpdatedBy() );
        monthlyReportDto.setMonth( monthlyReport.getMonth() );
        monthlyReportDto.setYear( monthlyReport.getYear() );

        return monthlyReportDto;
    }

    @Override
    public MonthlyReport monthlyReportToEntity(MonthlyReportDto monthlyReportDto) {
        if ( monthlyReportDto == null ) {
            return null;
        }

        MonthlyReport monthlyReport = new MonthlyReport();

        monthlyReport.setEgacData( egacDataToEntity( monthlyReportDto.getEgacDataDto() ) );
        if ( monthlyReportDto.getCreatedDate() != null ) {
            monthlyReport.setCreatedDate( LocalDateTime.ofInstant( monthlyReportDto.getCreatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( monthlyReportDto.getUpdatedDate() != null ) {
            monthlyReport.setUpdatedDate( LocalDateTime.ofInstant( monthlyReportDto.getUpdatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        monthlyReport.setCreatedBy( monthlyReportDto.getCreatedBy() );
        monthlyReport.setUpdatedBy( monthlyReportDto.getUpdatedBy() );
        monthlyReport.setId( monthlyReportDto.getId() );
        monthlyReport.setMonth( monthlyReportDto.getMonth() );
        monthlyReport.setYear( monthlyReportDto.getYear() );

        return monthlyReport;
    }

    @Override
    public MonthlyReportDto generalReportToMonthlyDto(GeneralReportDto generalReportDto) {
        if ( generalReportDto == null ) {
            return null;
        }

        MonthlyReportDto monthlyReportDto = new MonthlyReportDto();

        monthlyReportDto.setEgacDataDto( egacDataDtoToDto( generalReportDto.getEgacDataDto() ) );
        monthlyReportDto.setId( generalReportDto.getId() );
        monthlyReportDto.setCreatedDate( generalReportDto.getCreatedDate() );
        monthlyReportDto.setUpdatedDate( generalReportDto.getUpdatedDate() );
        monthlyReportDto.setCreatedBy( generalReportDto.getCreatedBy() );
        monthlyReportDto.setUpdatedBy( generalReportDto.getUpdatedBy() );

        return monthlyReportDto;
    }

    protected List<MonthlyReportDto> monthlyReportListToMonthlyReportDtoList(List<MonthlyReport> list) {
        if ( list == null ) {
            return null;
        }

        List<MonthlyReportDto> list1 = new ArrayList<MonthlyReportDto>( list.size() );
        for ( MonthlyReport monthlyReport : list ) {
            list1.add( monthlyReportToDto( monthlyReport ) );
        }

        return list1;
    }

    protected List<MonthlyReport> monthlyReportDtoListToMonthlyReportList(List<MonthlyReportDto> list) {
        if ( list == null ) {
            return null;
        }

        List<MonthlyReport> list1 = new ArrayList<MonthlyReport>( list.size() );
        for ( MonthlyReportDto monthlyReportDto : list ) {
            list1.add( monthlyReportToEntity( monthlyReportDto ) );
        }

        return list1;
    }
}
