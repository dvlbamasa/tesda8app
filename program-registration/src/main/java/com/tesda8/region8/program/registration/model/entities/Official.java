package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.util.enums.EducationalAttainment;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Official extends Auditable<String> {

    private String name;
    private String position;
    private String address;
    private String contactNumber;
    private String email;
    private String natureOfAppointment;
    @Enumerated(EnumType.STRING)
    private EducationalAttainment educationalAttainment;

    @ManyToOne
    @JoinColumn(name = "REGISTERED_PROGRAM_ID", nullable = false)
    @JsonBackReference
    private RegisteredProgram registeredProgram;
}
