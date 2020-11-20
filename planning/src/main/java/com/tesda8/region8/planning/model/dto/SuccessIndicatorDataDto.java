package com.tesda8.region8.planning.model.dto;

import com.tesda8.region8.util.enums.PapGroupType;
import lombok.Data;

import java.util.List;

@Data
public class SuccessIndicatorDataDto {

    private long id;
    private long papDataId;
    private String papName;
    private PapGroupType papGroupType;
    private int target;
    private String measures;
    private Boolean isAccumulated;
    private Boolean isPercentage;
    private Boolean isDeleted;
    private List<OperatingUnitDataDto> operatingUnitDataList;
}
