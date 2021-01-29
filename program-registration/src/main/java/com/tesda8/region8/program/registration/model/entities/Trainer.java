package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.program.registration.service.audit.listener.TrainerAuditListener;
import com.tesda8.region8.util.enums.EducationalAttainment;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.enums.Sex;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@EntityListeners(TrainerAuditListener.class)
@Entity
public class Trainer extends Auditable<String> {

    private String firstName;
    private String lastName;
    private String middleInitial;
    private String fullName;
    private String nameExt;
    private LocalDateTime birthdate;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String address;
    private String emailAddress;
    private String contactNumber;
    @Enumerated(EnumType.STRING)
    private EducationalAttainment educationalAttainment;
    private String trainingInstitution;
    @Enumerated(EnumType.STRING)
    private InstitutionType institutionType;
    private String teachingExperienceYear;
    private String industryExperienceYear;
    @ManyToOne
    @JoinColumn(name = "REGISTERED_PROGRAM_ID")
    @JsonManagedReference
    private RegisteredProgram registeredProgram;

    private String natureOfAppointment;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Certificate> certificates;
}
