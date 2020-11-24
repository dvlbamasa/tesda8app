package com.tesda8.region8.program.registration.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegistrationRequirementDto {
    private Date buildingOwnershipDateIssued;
    private Date fireSafetyDateIssued;
    private Date moaValidity;
}
