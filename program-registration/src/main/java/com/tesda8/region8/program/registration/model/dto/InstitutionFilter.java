package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstitutionFilter {

    private OperatingUnitType [] operatingUnitType;
    private Long [] institutionIds;
    private InstitutionType [] institutionType;
    private InstitutionClassification [] institutionClassification;
    private String address;
    private String contactNumber;
}
