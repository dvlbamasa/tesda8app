package com.tesda8.region8.planning.model.dto;

import com.tesda8.region8.planning.model.entities.OperatingUnitData;
import lombok.Data;

import java.util.List;

@Data
public class SuccessIndicatorDataDto {

    private long id;
    private int target;
    private String measures;
    private boolean isAccumulated;
    private List<OperatingUnitData> operatingUnitDataList;
}
