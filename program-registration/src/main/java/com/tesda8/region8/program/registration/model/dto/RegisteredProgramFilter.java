package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.enums.SortOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class RegisteredProgramFilter {

    private Long [] institutionIds;
    private Sector sector;
    private String courseName;
    private OperatingUnitType [] operatingUnitType;
    private InstitutionClassification [] institutionClassification;
    private String registeredProgramNumber;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateIssuedFrom;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateIssuedTo;
    private CourseStatus courseStatus;
    private Boolean isClosed;
    private SortOrder sortOrder;
}
