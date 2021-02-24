package com.tesda8.region8.web.service.impl.scholarship;

import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.util.enums.DataPointType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.model.DataPoints;
import com.tesda8.region8.web.service.ScholarshipDataPointService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipAssessedDataPointService implements ScholarshipDataPointService {

    @Override
    public List<DataPoints> getDataPoints(DataPointType dataPointType, List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList, List<DataPoints> dataPointsList) {
        scholarshipAccomplishmentDtoList.forEach(
                scholarshipAccomplishmentDto -> {
                    DataPoints dataPoints = new DataPoints();
                    dataPoints.setLabel(scholarshipAccomplishmentDto.getMonth().label);
                    checkDataPointType(dataPointType, dataPointsList, scholarshipAccomplishmentDto, dataPoints);
                }
        );
        return dataPointsList;
    }

    @Override
    public List<DataPoints> getDataPointsPerPo(DataPointType dataPointType, List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList, List<DataPoints> dataPointsList) {
        scholarshipAccomplishmentDtoList.forEach(
                scholarshipAccomplishmentDto -> {
                    DataPoints dataPoints = new DataPoints();
                    dataPoints.setLabel(scholarshipAccomplishmentDto.getOperatingUnitType().label);
                    checkDataPointType(dataPointType, dataPointsList, scholarshipAccomplishmentDto, dataPoints);
                }
        );
        return dataPointsList;    }

    private void checkDataPointType(DataPointType dataPointType, List<DataPoints> dataPointsList, ScholarshipAccomplishmentDto scholarshipAccomplishmentDto, DataPoints dataPoints) {
        switch (dataPointType) {
            case TARGET:
                dataPoints.setValue(scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getGraduates());
                break;
            case OUTPUT:
                dataPoints.setValue(scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getAssessed());
                break;
            case RATE:
                dataPoints.setValue(scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getAssessedUtilization().longValue());
                break;
            default:
                break;
        }
        dataPointsList.add(dataPoints);
    }

    @Override
    public EgacType supports() {
        return EgacType.ASSESSED;
    }
}
