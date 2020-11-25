package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.EducationalAttainment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainerDto {
    private Long id;
    private Long registeredProgramId;
    private String name;
    private String position;
    private String natureOfAppointment;
    private EducationalAttainment educationalAttainment;
    private Long teachingExperienceYear;
    private Long industryExperienceYear;
    private TrainerQualificationDto trainerQualification;
    private Boolean isDeleted;

    public TrainerDto(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
