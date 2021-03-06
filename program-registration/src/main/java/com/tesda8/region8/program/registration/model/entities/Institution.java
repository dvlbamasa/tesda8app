package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.program.registration.service.audit.listener.InstitutionAuditListener;
import com.tesda8.region8.program.registration.service.audit.listener.NonTeachingStaffAuditListener;
import com.tesda8.region8.util.enums.CongressionalDistrict;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(InstitutionAuditListener.class)
public class Institution extends Auditable<String> {

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<RegisteredProgram> registeredPrograms;

    private String name;

    private String shortName;

    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    private String address;

    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private InstitutionType institutionType;

    @Enumerated(EnumType.STRING)
    private CongressionalDistrict congressionalDistrict;

    @Enumerated(EnumType.STRING)
    private InstitutionClassification institutionClassification;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;
}
