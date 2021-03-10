package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.CongressionalDistrict;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RegisteredProgramDto {

    private long id;
    private OperatingUnitType operatingUnit;
    private String institutionShortName;
    private String institutionName;
    private InstitutionClassification institutionClassification;
    private InstitutionType institutionType;
    private CongressionalDistrict congressionalDistrict;
    private String address;
    private String contactNumber;

    private String name;
    private int duration;
    private String programRegistrationNumber;
    private LocalDateTime dateIssued;
    private CourseStatus courseStatus;
    private Sector sector;
    private Long numberOfTeachers;
    private Boolean isClosed;
    private Boolean isDeleted;
}
