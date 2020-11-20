package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.Sector;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisteredProgramDto {

    private long id;
    private String operatingUnit;
    private String institutionName;
    private String institutionClassification;
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
