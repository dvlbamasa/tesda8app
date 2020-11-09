package com.tesda8.region8.planning.model.wrapper;

import com.tesda8.region8.util.enums.PapGroupType;
import lombok.Data;

@Data
public class PapDataFilterRequest {

    private PapGroupType papGroupType;
    private String papName;
    private String successIndicatorMeasure;
}