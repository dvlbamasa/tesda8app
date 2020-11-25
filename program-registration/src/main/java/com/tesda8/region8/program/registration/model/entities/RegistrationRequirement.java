package com.tesda8.region8.program.registration.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@NoArgsConstructor
@Embeddable
public class RegistrationRequirement {

    private Date buildingOwnershipDateIssued;
    private Date fireSafetyDateIssued;
    private Date moaValidity;
}
