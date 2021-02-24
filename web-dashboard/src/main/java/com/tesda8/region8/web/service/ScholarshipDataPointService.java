package com.tesda8.region8.web.service;

import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.util.enums.DataPointType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.model.DataPoints;

import java.util.List;

public interface ScholarshipDataPointService {

    List<DataPoints> getDataPoints(DataPointType dataPointType,
                                   List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList,
                                   List<DataPoints> dataPointsList);

    List<DataPoints> getDataPointsPerPo(DataPointType dataPointType,
                                        List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList,
                                        List<DataPoints> dataPointsList);

    EgacType supports();
}
