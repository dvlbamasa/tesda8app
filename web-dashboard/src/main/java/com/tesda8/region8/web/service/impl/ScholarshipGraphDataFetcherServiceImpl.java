package com.tesda8.region8.web.service.impl;

import com.google.common.collect.Lists;
import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.scholarship.service.impl.ScholarshipAccomplishmentServiceImpl;
import com.tesda8.region8.util.enums.DataPointType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ScholarshipType;
import com.tesda8.region8.util.model.DataPoints;
import com.tesda8.region8.web.model.dto.graph.GraphDataList;
import com.tesda8.region8.web.service.ScholarshipDataPointService;
import com.tesda8.region8.web.service.ScholarshipGraphDataFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScholarshipGraphDataFetcherServiceImpl implements ScholarshipGraphDataFetcherService {

    private final ScholarshipAccomplishmentServiceImpl scholarshipAccomplishmentService;
    private final List<ScholarshipDataPointService> scholarshipDataPointServiceList;

    private List<DataPoints> fetchDataPoints(DataPointType dataPointType,
                                                     EgacType egacType,
                                                     Long year,
                                                     OperatingUnitType operatingUnitType,
                                                     ScholarshipType scholarshipType) throws ServiceNotFoundException {
        List<DataPoints> dataPointsList = Lists.newArrayList();
        List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentList = scholarshipAccomplishmentService
                .getAllScholarshipAccomplishmentByYearAndOperatingUnitAndScholarshipType(year, operatingUnitType, scholarshipType);
        List<ScholarshipAccomplishmentDto> sortedScholarshipAccomplishmentList = Lists.newArrayList();
        Arrays.asList(Month.values()).forEach(
                month -> {
                    scholarshipAccomplishmentList.forEach(
                            scholarshipAccomplishmentDto -> {
                                if (scholarshipAccomplishmentDto.getMonth().equals(month)) {
                                    sortedScholarshipAccomplishmentList.add(scholarshipAccomplishmentDto);
                                }
                            }
                    );
                }
        );
        ScholarshipDataPointService service = scholarshipDataPointServiceList
                .stream()
                .filter(scholarshipDataPointService -> scholarshipDataPointService.supports().equals(egacType))
                .findFirst().orElseThrow(ServiceNotFoundException::new);
        return service.getDataPoints(dataPointType, sortedScholarshipAccomplishmentList, dataPointsList);
    }

    @Override
    public GraphDataList fetchMonthlyGraphDataList(Long year, EgacType egacType, OperatingUnitType operatingUnitType, ScholarshipType scholarshipType) throws ServiceNotFoundException {
        GraphDataList graphDataList = new GraphDataList().build();
        graphDataList.getTargetData().setDataPoints(fetchDataPoints(DataPointType.TARGET, egacType, year, operatingUnitType, scholarshipType));
        graphDataList.getOutputData().setDataPoints(fetchDataPoints(DataPointType.OUTPUT, egacType, year, operatingUnitType, scholarshipType));
        graphDataList.getRateData().setDataPoints(fetchDataPoints(DataPointType.RATE, egacType, year, operatingUnitType, scholarshipType));
        return graphDataList;
    }
}
