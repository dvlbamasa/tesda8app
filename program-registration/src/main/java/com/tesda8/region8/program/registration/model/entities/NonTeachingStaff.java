package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.program.registration.service.audit.listener.NonTeachingStaffAuditListener;
import com.tesda8.region8.util.enums.EducationalAttainment;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(NonTeachingStaffAuditListener.class)
public class NonTeachingStaff extends Auditable<String> {

    private String name;
    private String position;
    private String natureOfAppointment;
    @Enumerated(EnumType.STRING)
    private EducationalAttainment educationalAttainment;
    private String positionExperience;

    @ManyToOne
    @JoinColumn(name = "REGISTERED_PROGRAM_ID", nullable = false)
    @JsonManagedReference
    private RegisteredProgram registeredProgram;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;
}
