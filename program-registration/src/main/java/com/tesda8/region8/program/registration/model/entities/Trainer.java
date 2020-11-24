package com.tesda8.region8.program.registration.model.entities;

import com.tesda8.region8.util.enums.EducationalAttainment;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Embeddable
public class Trainer {

    private String name;
    private String position;
    private String natureOfAppointment;
    @Enumerated(EnumType.STRING)
    private EducationalAttainment educationalAttainment;
    private Long teachingExperienceYear;
    private Long industryExperienceYear;
    @Embedded
    private TrainerQualification trainerQualification;
}
