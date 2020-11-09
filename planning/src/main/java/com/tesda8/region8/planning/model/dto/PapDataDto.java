package com.tesda8.region8.planning.model.dto;

import com.tesda8.region8.planning.model.entities.SuccessIndicatorData;
import com.tesda8.region8.util.enums.PapGroupType;
import lombok.Data;

import java.util.List;

@Data
public class PapDataDto {

    private long id;
    private String name;
    private PapGroupType papGroupType;
    List<SuccessIndicatorDataDto> successIndicatorDataList;
}