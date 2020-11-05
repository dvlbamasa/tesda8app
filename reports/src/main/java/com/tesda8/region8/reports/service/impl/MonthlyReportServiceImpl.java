package com.tesda8.region8.reports.service.impl;

import com.google.common.collect.Lists;
import com.tesda8.region8.util.service.ReportUtil;
import com.tesda8.region8.reports.model.dto.EgacDataDto;
import com.tesda8.region8.reports.model.dto.GeneralReportDto;
import com.tesda8.region8.reports.model.dto.MonthlyReportDto;
import com.tesda8.region8.reports.model.entities.MonthlyReport;
import com.tesda8.region8.reports.model.entities.OperatingUnit;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import com.tesda8.region8.reports.repository.MonthlyReportRepository;
import com.tesda8.region8.reports.repository.OperatingUnitRepository;
import com.tesda8.region8.reports.service.MonthlyReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthlyReportServiceImpl implements MonthlyReportService {

    private ReportMapper reportMapper;
    private OperatingUnitRepository operatingUnitRepository;
    private MonthlyReportRepository monthlyReportRepository;

    private static Logger logger = LoggerFactory.getLogger(MonthlyReportServiceImpl.class);


    @Autowired
    public MonthlyReportServiceImpl(ReportMapper reportMapper,
                                    OperatingUnitRepository operatingUnitRepository,
                                    MonthlyReportRepository monthlyReportRepository) {
        this.reportMapper = reportMapper;
        this.operatingUnitRepository = operatingUnitRepository;
        this.monthlyReportRepository = monthlyReportRepository;
    }

    @Override
    public void updateMonthlyReport(List<GeneralReportDto> monthlyReports, Month month, int year) {

        // delete previous data
        deleteMonthlyReport(month, year);


        Arrays.asList(OperatingUnitType.values()).forEach(
            operatingUnitType -> {
                if (!operatingUnitType.equals(OperatingUnitType.TOTAL)) {
                    List<MonthlyReportDto> newMonthlyReport = monthlyReports
                            .stream()
                            .filter(generalReportDto -> generalReportDto.getOperatingUnitType().equals(operatingUnitType))
                            .map( generalReportDto -> reportMapper.generalReportToMonthlyDto(generalReportDto))
                            .collect(Collectors.toList());

                    newMonthlyReport.forEach(
                            monthlyReportDto -> {
                                monthlyReportDto.setYear(year);
                                monthlyReportDto.setMonth(month);
                                monthlyReportDto.getEgacDataDto().setRate(ReportUtil.calculateRate(monthlyReportDto.getEgacDataDto().getTarget(),
                                        monthlyReportDto.getEgacDataDto().getOutput()));
                            }
                    );
                    List<MonthlyReport> finalMonthlyReports = newMonthlyReport
                            .stream()
                            .map(monthlyReportDto -> reportMapper.monthlyReportToEntity(monthlyReportDto))
                            .collect(Collectors.toList());

                    OperatingUnit operatingUnit = operatingUnitRepository.getByOperatingUnitTypeOrderById(operatingUnitType);
                    List<MonthlyReport> monthlyReportList = Lists.newArrayList();

                    finalMonthlyReports.forEach(monthlyReport -> {
                        monthlyReport.setOperatingUnit(operatingUnit);
                        monthlyReportList.add(monthlyReportRepository.save(monthlyReport));
                    });
                    operatingUnit.getMonthlyReports().addAll(monthlyReportList);
                    operatingUnitRepository.save(operatingUnit);
                }
            }
        );

        List<MonthlyReportDto> totalMonthlyReport = Lists.newArrayList();

        Arrays.asList(EgacType.values())
                .forEach(egacType -> {
                    MonthlyReportDto monthlyReportDto = new MonthlyReportDto();
                    EgacDataDto egacDataDto = new EgacDataDto();
                    egacDataDto.setTarget(0);
                    egacDataDto.setOutput(0);
                    monthlyReportDto.setEgacDataDto(egacDataDto);
                    List<MonthlyReportDto> newMonthlyReports = monthlyReports
                            .stream()
                            .filter(generalReportDto -> generalReportDto.getEgacDataDto().getEgacType().equals(egacType))
                            .map( generalReportDto -> reportMapper.generalReportToMonthlyDto(generalReportDto))
                            .collect(Collectors.toList());

                    newMonthlyReports.forEach(monthlyReportDto1 -> {
                        monthlyReportDto.getEgacDataDto().setTarget(monthlyReportDto1.getEgacDataDto().getTarget() + monthlyReportDto.getEgacDataDto().getTarget());
                        monthlyReportDto.getEgacDataDto().setOutput(monthlyReportDto1.getEgacDataDto().getOutput() + monthlyReportDto.getEgacDataDto().getOutput());
                    });
                    monthlyReportDto.getEgacDataDto().setEgacType(egacType);
                    monthlyReportDto.setMonth(month);
                    monthlyReportDto.setYear(year);
                    monthlyReportDto.getEgacDataDto().setRate(ReportUtil.calculateRate(monthlyReportDto.getEgacDataDto().getTarget(), monthlyReportDto.getEgacDataDto().getOutput()));
                    totalMonthlyReport.add(monthlyReportDto);
                });

        List<MonthlyReport> finalMonthlyReports = totalMonthlyReport
                .stream()
                .map(monthlyReportDto -> reportMapper.monthlyReportToEntity(monthlyReportDto))
                .collect(Collectors.toList());

        OperatingUnit operatingUnit = operatingUnitRepository.getByOperatingUnitTypeOrderById(OperatingUnitType.TOTAL);

        List<MonthlyReport> monthlyReportList = Lists.newArrayList();
        finalMonthlyReports.forEach(monthlyReport -> {
            monthlyReport.setOperatingUnit(operatingUnit);
            monthlyReportList.add(monthlyReportRepository.save(monthlyReport));
        });
        operatingUnit.getMonthlyReports().addAll(monthlyReportList);
        operatingUnitRepository.save(operatingUnit);
    }

    @Override
    public void deleteMonthlyReport(Month month, int year) {
        List<MonthlyReport> monthlyReportList = monthlyReportRepository.findAll();
        List<MonthlyReport> monthlyReportsToBeDeleted = monthlyReportList
                .stream().filter(monthlyReport -> monthlyReport.getMonth().equals(month) && monthlyReport.getYear() == year)
                .collect(Collectors.toList());
        if (monthlyReportsToBeDeleted.size() > 0) {
            monthlyReportsToBeDeleted.forEach(
                    monthlyReport -> {
                        monthlyReportRepository.delete(monthlyReport);
                    }
            );
        }
    }
}
