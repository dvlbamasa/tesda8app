package com.tesda8.region8.planning.model.dto;

import com.tesda8.region8.util.enums.PapGroupType;
import com.tesda8.region8.util.enums.SuccessIndicatorType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
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
    private SuccessIndicatorType successIndicatorType;
    private List<OperatingUnitDataDto> operatingUnitDataList;
    private List<OperatingUnitDataDto> ttiDataList;

}
