package com.tesda8.region8.program.registration.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RegistrationRequirementDto {
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date buildingOwnershipDateIssued;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date fireSafetyDateIssued;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date moaValidity;
}
