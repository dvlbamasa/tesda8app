package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
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

    @ElementCollection
    @CollectionTable(
            name = "TRAINER",
            joinColumns = @JoinColumn(name = "REGISTERED_PROGRAM_ID")
    )
    private List<Trainer> trainerList;

    @ElementCollection
    @CollectionTable(
            name = "OFFICIAL",
            joinColumns = @JoinColumn(name = "REGISTERED_PROGRAM_ID")
    )
    private List<Official> officialList;

    @ElementCollection
    @CollectionTable(
            name = "NON_TEACHING_STAFF",
            joinColumns = @JoinColumn(name = "REGISTERED_PROGRAM_ID")
    )
    private List<NonTeachingStaff> nonTeachingStaffList;
}
