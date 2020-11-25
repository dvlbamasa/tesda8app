package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.CongressionalDistrict;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class InstitutionDto {

    private long id;
    private List<RegisteredProgramDto> registeredPrograms;
    private String name;
    private String shortName;
    private OperatingUnitType operatingUnitType;
    private String address;
    private String contactNumber;
    private InstitutionType institutionType;
    private CongressionalDistrict congressionalDistrict;
    private InstitutionClassification institutionClassification;

}
