package com.tesda8.region8.reports.model.dto;

import com.tesda8.region8.util.enums.EgacType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EgacDataDto {

    private EgacType egacType;
    private long target;
    private long output;
    private double rate;

}
