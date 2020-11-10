package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RegisteredProgramFilter {

    private String [] institutionNames;
    private Sector sector;
    private String courseName;
    private OperatingUnitType [] operatingUnitType;
    private InstitutionClassification institutionClassification;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateIssuedFrom;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateIssuedTo;
}
