package com.tesda8.region8.program.registration.model.entities;

import com.tesda8.region8.util.enums.MoaValidityType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@NoArgsConstructor
@Embeddable
public class RegistrationRequirement {

    private Date buildingOwnershipDateIssued;
    private Date fireSafetyDateIssued;
    private Date moaValidity;
    @Enumerated(EnumType.STRING)
    private MoaValidityType moaValidityType;
}
