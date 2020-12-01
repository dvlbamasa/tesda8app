package com.tesda8.region8.program.registration.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpiredStatusCount {

    private Long expiredBuildingOwnership;
    private Long expiredFireSafety;
    private Long moaValidity;
    private Long total;
}
