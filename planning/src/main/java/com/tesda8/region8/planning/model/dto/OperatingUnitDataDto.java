package com.tesda8.region8.planning.model.dto;

import com.tesda8.region8.util.enums.OperatingUnitPOType;
import lombok.Data;

@Data
public class OperatingUnitDataDto {

    private long id;
    private long target;
    private long output;
    private double rate;
    private OperatingUnitPOType operatingUnitType;

}
