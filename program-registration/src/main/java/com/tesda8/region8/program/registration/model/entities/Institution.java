package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.util.enums.CongressionalDistrict;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "INSTITUTION")
public class Institution extends GeneralData {

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RegisteredProgram> registeredPrograms;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "OPERATING_UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "INSTITUTION_TYPE")
    @Enumerated(EnumType.STRING)
    private InstitutionType institutionType;

    @Column(name = "CONGRESSIONAL_DISTRICT")
    @Enumerated(EnumType.STRING)
    private CongressionalDistrict congressionalDistrict;

    @Column(name = "INSTITUTION_CLASSIFICATION")
    @Enumerated(EnumType.STRING)
    private InstitutionClassification institutionClassification;
}
