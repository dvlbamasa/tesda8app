package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.program.registration.service.audit.listener.NonTeachingStaffAuditListener;
import com.tesda8.region8.program.registration.service.audit.listener.RegisteredProgramAuditListener;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.Sector;
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

@Data
@NoArgsConstructor
@Entity
@EntityListeners(RegisteredProgramAuditListener.class)
public class RegisteredProgram extends Auditable<String> {

    private String name;

    private int duration;

    private String programRegistrationNumber;

    private LocalDateTime dateIssued;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    @JsonBackReference
    private Institution institution;

    private Long numberOfTeachers;

    @Type(type = "yes_no")
    private Boolean isClosed = false;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;

    @Embedded
    private RegistrationRequirement registrationRequirement;

    @OneToMany(mappedBy = "registeredProgram", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Trainer> trainerList;

    @OneToMany(mappedBy = "registeredProgram", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Official> officialList;

    @OneToMany(mappedBy = "registeredProgram", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<NonTeachingStaff> nonTeachingStaffList;
}
