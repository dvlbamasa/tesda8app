package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "REGISTERED_PROGRAM")
public class RegisteredProgram  extends GeneralData {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DURATION")
    private int duration;

    @Column(name = "PROGRAM_REGISTRATION_NUMBER")
    private String programRegistrationNumber;

    @Column(name = "DATE_ISSUED")
    private LocalDateTime dateIssued;

    @Column(name = "COURSE_STATUS")
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Column(name = "SECTOR")
    @Enumerated(EnumType.STRING)
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    @JsonBackReference
    private Institution institution;

    @Column(name = "NUMBER_OF_TEACHERS")
    private Long numberOfTeachers;

    @Column(name = "IS_CLOSED")
    @Type(type = "yes_no")
    private Boolean isClosed = false;

    @Column(name = "IS_DELETED")
    @Type(type = "yes_no")
    private Boolean isDeleted = false;
}
