package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.Sector;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RegisteredProgramRequestDto {

    private long id;
    private long institutionId;
    private String institutionName;
    private String name;
    private int duration;
    private String programRegistrationNumber;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateIssued;
    private CourseStatus courseStatus;
    private Sector sector;
    private Long numberOfTeachers;
    private Boolean isClosed;
    private Boolean isDeleted;
    private RegistrationRequirementDto registrationRequirement;
    private List<TrainerDto> trainerDtoList;
    private List<OfficialDto> officialDtoList;
    private List<NonTeachingStaffDto> nonTeachingStaffDtoList;
}
