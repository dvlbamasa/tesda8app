package com.tesda8.region8.scholarship.model.entities;

import com.tesda8.region8.scholarship.service.audit.listener.ScholarshipAccomplishmentAuditListener;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ScholarshipType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(ScholarshipAccomplishmentAuditListener.class)
public class ScholarshipAccomplishment extends Auditable<String> {

    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    @Enumerated(EnumType.STRING)
    private ScholarshipType scholarshipType;

    @Enumerated(EnumType.STRING)
    private Month month;

    private Long year;

    @Embedded
    private QualificationMap qualificationMap;

    @Embedded
    private FinancialAccomplishment financialAccomplishment;

    @Embedded
    private PhysicalAccomplishment physicalAccomplishment;
}
