package com.tesda8.region8.web.service.impl;

import com.google.common.collect.Lists;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.service.PapDataService;
import com.tesda8.region8.util.enums.DataPointType;
import com.tesda8.region8.util.model.DataPoints;
import com.tesda8.region8.web.model.dto.graph.GraphData;
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
    public GraphDataList fetchOPCRDataList(Long successIndicatorId) {
        GraphDataList graphDataList = new GraphDataList();
        GraphData targetData = new GraphData();
        GraphData outputData = new GraphData();
        GraphData rateData = new GraphData();
        targetData.setDataPoints(fetchDataPoints(DataPointType.TARGET, successIndicatorId));
        outputData.setDataPoints(fetchDataPoints(DataPointType.OUTPUT, successIndicatorId));
        rateData.setDataPoints(fetchDataPoints(DataPointType.RATE, successIndicatorId));
        graphDataList.setTargetData(targetData);
        graphDataList.setOutputData(outputData);
        graphDataList.setRateData(rateData);
        return graphDataList;
    }

    private List<DataPoints> fetchDataPoints(DataPointType dataPointType, Long successIndicatorId) {
        SuccessIndicatorDataDto successIndicatorDataDto = papDataService.getSuccessIndicatorData(successIndicatorId);
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
