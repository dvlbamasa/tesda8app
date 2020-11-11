package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;

@Data
public class InstitutionFilter {

    private OperatingUnitType [] operatingUnitType;
    private String [] institutionNames;
    private InstitutionType [] institutionType;
    private InstitutionClassification [] institutionClassification;
    private String address;
    private String contactNumber;
}
