package com.tesda8.region8.planning.model.dto;

import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitPOType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperatingUnitDataDto {

    private long id;
    private long target;
    private long output;
    private double rate;
    private Month month;
    private OperatingUnitPOType operatingUnitType;

}
