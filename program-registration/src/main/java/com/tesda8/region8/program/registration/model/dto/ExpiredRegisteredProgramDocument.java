package com.tesda8.region8.program.registration.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpiredRegisteredProgramDocument {

    private String programRegistrationNumber;
    private String qualification;
    private String institutionName;
    private Long id;
    private boolean expiredBuildingOwnership;
    private boolean expiredFireSafety;
    private boolean expiredMoaValidity;


}
