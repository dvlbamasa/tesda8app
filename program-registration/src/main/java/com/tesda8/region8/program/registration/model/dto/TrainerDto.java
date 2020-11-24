package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.EducationalAttainment;
import lombok.Data;


@Data
public class TrainerDto {
    private String name;
    private String position;
    private String natureOfAppointment;
    private EducationalAttainment educationalAttainment;
    private Long teachingExperienceYear;
    private Long industryExperienceYear;
    private TrainerQualificationDto trainerQualification;
}
