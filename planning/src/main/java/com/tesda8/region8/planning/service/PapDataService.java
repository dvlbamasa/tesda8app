package com.tesda8.region8.planning.service;

import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.model.wrapper.PapDataWrapper;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.PapGroupType;

import java.util.List;

public interface PapDataService {

    List<PapDataDto> getAllPapData();

    List<PapDataDto> getAllPapDataByYear(Long year);

    List<PapDataDto> getAllPapDataByPapGroupType(PapGroupType papGroupType);

    PapDataWrapper getAllPapDataWrapperByFilter(String measureFilter, String papName, Long year, Month month, String role, String pageType);

    List<SuccessIndicatorDataDto> getAllSuccessIndicatorsByFilter(PapGroupType papGroupType, String measureFilter,
                                                                  String papName, Long year, Month month, String pageType);

    SuccessIndicatorDataDto getSuccessIndicatorData(Long id, String pageType);

    void updatePapData(List<SuccessIndicatorDataDto> successIndicatorDataDtoList, String updateType, Month month);

    void updateSuccessIndicators(List<SuccessIndicatorDataDto> successIndicatorDataDtoList);

    void createSuccessIndicator(SuccessIndicatorDataDto successIndicatorDataDto);

    void createPapData(PapDataDto papDataDto, Long year);

    void deletePapData(PapDataDto papDataDto);
}
