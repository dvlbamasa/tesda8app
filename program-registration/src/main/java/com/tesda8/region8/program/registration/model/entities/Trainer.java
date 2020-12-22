package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.program.registration.service.audit.listener.OfficialAuditListener;
import com.tesda8.region8.program.registration.service.audit.listener.TrainerAuditListener;
import com.tesda8.region8.util.enums.EducationalAttainment;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@EntityListeners(TrainerAuditListener.class)
@Entity
public class Trainer extends Auditable<String> {

    private String name;
    private String position;
    private String natureOfAppointment;
    @Enumerated(EnumType.STRING)
    private EducationalAttainment educationalAttainment;
    private Long teachingExperienceYear;
    private Long industryExperienceYear;
    @Embedded
    private TrainerQualification trainerQualification;

    @ManyToOne
    @JoinColumn(name = "REGISTERED_PROGRAM_ID", nullable = false)
    @JsonBackReference
    private RegisteredProgram registeredProgram;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;
}
