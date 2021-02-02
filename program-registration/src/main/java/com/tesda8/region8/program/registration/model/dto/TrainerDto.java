package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.program.registration.model.entities.NatureOfAppointmentDetails;
import com.tesda8.region8.program.registration.model.entities.RemarkDetails;
import com.tesda8.region8.util.enums.EducationalAttainment;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class TrainerDto {
    private Long id;
    private Long registeredProgramId;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String fullName;
    private String nameExt;
    private LocalDateTime birthdate;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthdateRequest;

    private Sex sex;
    private String address;
    private String emailAddress;
    private String contactNumber;

    private NatureOfAppointmentDetails natureOfAppointmentDetails;
    private RemarkDetails remarkDetails;

    private EducationalAttainment educationalAttainment;
    private String trainingInstitution;
    private InstitutionType institutionType;

    private String teachingExperienceYear;
    private String industryExperienceYear;

    private Boolean isDeleted;

    private List<CertificateDto> certificates;

    private List<CertificateDto> ncCertificates;
    private List<CertificateDto> tmCertificates;
    private List<CertificateDto> nttcCertificates;


    public TrainerDto(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
