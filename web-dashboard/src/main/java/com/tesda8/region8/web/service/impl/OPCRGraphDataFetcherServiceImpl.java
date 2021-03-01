package com.tesda8.region8.web.service.impl;

import com.google.common.collect.Lists;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.service.PapDataService;
import com.tesda8.region8.util.enums.DataPointType;
import com.tesda8.region8.util.model.DataPoints;
import com.tesda8.region8.web.model.dto.graph.GraphDataList;
import com.tesda8.region8.web.service.OPCRGraphDataFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OPCRGraphDataFetcherServiceImpl implements OPCRGraphDataFetcherService {

    private PapDataService papDataService;

    @Autowired
    public OPCRGraphDataFetcherServiceImpl(PapDataService papDataService) {
        this.papDataService = papDataService;
    }

    @Override
    public GraphDataList fetchOPCRDataList(Long successIndicatorId, String pageType) {
        GraphDataList graphDataList = new GraphDataList().build();
        graphDataList.getTargetData().setDataPoints(fetchDataPoints(DataPointType.TARGET, successIndicatorId, pageType));
        graphDataList.getOutputData().setDataPoints(fetchDataPoints(DataPointType.OUTPUT, successIndicatorId, pageType));
        graphDataList.getRateData().setDataPoints(fetchDataPoints(DataPointType.RATE, successIndicatorId, pageType));
        return graphDataList;
    }

    private List<DataPoints> fetchDataPoints(DataPointType dataPointType, Long successIndicatorId, String pageType) {
        SuccessIndicatorDataDto successIndicatorDataDto = papDataService.getSuccessIndicatorData(successIndicatorId, pageType);
        List<DataPoints> dataPoints = Lists.newArrayList();
        successIndicatorDataDto.getOperatingUnitDataList().forEach(
                operatingUnitDataDto -> {
                    DataPoints newDataPoints = new DataPoints();
                    newDataPoints.setLabel(operatingUnitDataDto.getOperatingUnitType().label);
                    switch (dataPointType) {
                        case TARGET:
                            newDataPoints.setValue(operatingUnitDataDto.getTarget());
                            break;
                        case OUTPUT:
                            newDataPoints.setValue(operatingUnitDataDto.getOutput());
                            break;
                        case RATE:
                            newDataPoints.setValue(Double.valueOf(operatingUnitDataDto.getRate()).longValue());
                            break;
                        default:
                            break;
                    }
                    dataPoints.add(newDataPoints);
                }
        );
        return dataPoints;
    }
}
