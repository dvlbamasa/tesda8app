package com.tesda8.region8.planning.service;

import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.util.enums.PapGroupType;

import java.util.List;

public interface PapDataService {

    List<PapDataDto> getAllPapData();

    List<PapDataDto> getAllPapDataByPapGroupType(PapGroupType papGroupType);

    List<PapDataDto> getAllPapDataByPapGroupTypeAndMeasureAndPapName(PapGroupType papGroupType, String measureFilter, String papName);

    void updatePapData(List<PapDataDto> papDataDtoList);

    void updateSuccessIndicators(List<PapDataDto> papDataDtoList);

    void createSuccessIndicator(SuccessIndicatorDataDto successIndicatorDataDto);

    void createPapData(PapDataDto papDataDto);

    void deletePapData(PapDataDto papDataDto);
}
